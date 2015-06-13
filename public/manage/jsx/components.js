var Button = ReactBootstrap.Button;
var Table = ReactBootstrap.Table;
var Input = ReactBootstrap.Input;

var TalkEditor = React.createClass({
	mixins: [Flux.mixins.storeListener],
	
	getInitialState: function() {
		return {
		  talk: {}
	    };
	},
	
	componentDidMount: function() {
		var target = this;

		$('#startDate').datetimepicker({format: 'YYYY-M-D H:m'});
		$('#startDate').on("dp.change", function (e) {
			target.handleFillIn(e);
		});

		$('#endDate').datetimepicker({format: 'YYYY-M-D H:m'});
		$('#endDate').on("dp.change", function (e) {
			target.handleFillIn(e);
		});

		var t = {};
		t.topic = '';
		t.description = '';
		t.startDate = '';
		t.endDate = '';
		this.setState({talk: t});
	},
	
//	guid: function() {
//		  function s4() {
//		    return Math.floor((1 + Math.random()) * 0x10000)
//		      .toString(16)
//		      .substring(1);
//		  }
//		  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
//		    s4() + '-' + s4() + s4() + s4();
//		},
	
  componentDidUpdate: function() {
	  var tar = this.getStore('store').editTarget;
	  if (tar) { 
		  this.setState({talk: tar});
		  Dispatcher.dispatch('clearEditItem');
	  }
	  
  },
  
  render: function() {
	var button;
	if (this.state.talk.sessionid) {
		button = (<span>
			<Button bsStyle='primary' onClick={this.handleUpdate}>update</Button>
			<Button bsStyle='primary' onClick={this.handleDelete}>delete</Button>
			</span>
		);
	} else {
		button = <Button bsStyle='primary' onClick={this.handlePost}>create</Button>;
	} 
    return (
      <div>
        <Input label="Topic"       name="topic"       type="text" value={this.state.talk.topic} placeholder="Enter Text" onChange={this.handleFillIn} autoFocus  />
        <Input label="Description" name="description" type="textarea" value={this.state.talk.description} placeholder="Enter Text" onChange={this.handleFillIn}  />
        <Input label="Start Time"  name="startDate"   type='text' value={this.state.talk.startDate} placeholder="Select One Date Time" id='startDate' />
        <Input label="End Time"    name="endDate"     type='text' value={this.state.talk.endDate} placeholder="Select One Date Time" id='endDate' />
        {button}
        <Button bsStyle='primary' onClick={this.clear}>cancel</Button>
      </div>
    );
  }, 
  
  handleFillIn: function(e) {
	var name = e.target.name;
	var value = e.target.value;

	var t = this.state.talk;
	if (name === 'topic') {
		t.topic = value;
	} else if (name === 'description') {
		t.description = value;
	} else if (name === 'startDate') {
		t.startDate = value;
	} else if (name === 'endDate') {
		t.endDate = value;
	}
	this.setState({talk: t});
  },
  
  handlePost: function() {
	  var obj = {
//	    id: this.guid(),
	    topic: this.state.talk.topic,
	    description: this.state.talk.description,
	    startDate: this.state.talk.startDate,
	    endDate: this.state.talk.endDate
	  };

	  $.ajax({
          url: this.props.toUrl,
          type: 'POST',
          data: JSON.stringify(obj),
          contentType: 'application/json',
          success: function(data) {
        	  Dispatcher.dispatch('reloadData');
        	  this.clear();
          }.bind(this)
      });
  },
  
  handleUpdate: function() {
	  var obj = {
	    topic: this.state.talk.topic,
	    description: this.state.talk.description,
	    startDate: this.state.talk.startDate,
	    endDate: this.state.talk.endDate
	  };

	  $.ajax({
          url: this.state.talk._links.self.href,
          type: 'PUT',
          data: JSON.stringify(obj),
          contentType: 'application/json',
          success: function(data) {
        	  Dispatcher.dispatch('reloadData');
        	  this.clear();
          }.bind(this)
      });
  },

  handleDelete: function() {
	  $.ajax({
		  url: this.state.talk._links.self.href,
		  type: 'DELETE',
//		  data: JSON.stringify(obj),
//		  contentType: 'application/json',
		  success: function(data) {
			  Dispatcher.dispatch('reloadData');
			  this.clear();
		  }.bind(this)
	  });
  },
  
  clear: function() {
	  $('.form-control').val('');
	  this.setState({talk: {}});
  },
  
});




var TalksPanel = React.createClass({
	mixins: [Flux.mixins.storeListener],
	
	componentDidMount: function() {
	    this.loadTalks();
	},

	render: function() {
		var _this = this;
		var	list = this.getStore('store').talks.map(function(it){
			return (
			<tr key={it.sessionid} id={it.sessionid} onClick={_this.handleTalkSelected}>
				<td>{it.topic}</td>
				<td>{it.description}</td>
				<td>{it.startDate}</td>
				<td>{it.endDate}</td>
			</tr>
			);
		});
	    return (<Table striped bordered condensed hover>
	    			<thead>
	    			<tr><th>Topic</th><th>Description</th><th>Start Time</th><th>End Time</th></tr>
	    			</thead>
	    			<tbody>
	    			{list}
	    			</tbody>
	    		</Table>);
	 },
	 
	 handleTalkSelected: function(e) {
		 Dispatcher.dispatch('editItem', e.currentTarget.id);
	 },
	 
	 loadTalks: function() {
	   $.get(this.props.fromUrl, function(re) {
	    	if (re._embedded) {
	    		Dispatcher.dispatch('init', re._embedded.talks);
	    	}
	    	
	    	if (re.page) {
	    		this.setState({page: re.page});
	    	}
	    	
	    }.bind(this)); 
	 }
});
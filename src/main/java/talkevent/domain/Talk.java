package talkevent.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

//@Entity
@NodeEntity
public class Talk {

//	@Id
	@GraphId
//	@Column(name="id", nullable=false, updatable=false)
	private Long id;

	@NotNull
//	@Column(name="topic", nullable=false)
	private String topic;

//	@Column(name="description")
	private String description;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd hh:mm")
//	@Column(name="startDate")
	private Date startDate;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd hh:mm")
//	@Column(name="endDate")
	private Date endDate;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


}

package talkevent.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Talk {

	@Id
	@Column(name="id", nullable=false, updatable=false)
	private String id;

	@Column(name="topic", nullable=false)
	@NotNull
	private String topic;
//	private String description;
//	private Date startDate;
//	private Date endDate;

	public String getId() {
		return id;
	}

	public String getTopic() {
		return topic;
	}
	
	Talk() {}
	Talk(String id, String topic) {
		this.id = id;
		this.topic = topic;
	}
	
	public static Talk create(String topic) {
		return new Talk(null, topic);
	}

	public void setId(String id) {
		this.id = id;
	}

}

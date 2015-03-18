package talkevent.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Talk {

	@Id
	@Column(name="id", nullable=false, updatable=false)
	@NotNull
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
	
}

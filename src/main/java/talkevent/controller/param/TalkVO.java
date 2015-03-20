package talkevent.controller.param;

import javax.validation.constraints.NotNull;

public class TalkVO {

	@NotNull
	private String topic;
//	private String description;
//	private Date startDate;
//	private Date endDate;

	public String getTopic() {
		return topic;
	}

}

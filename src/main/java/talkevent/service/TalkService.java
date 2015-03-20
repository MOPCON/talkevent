package talkevent.service;

import java.util.List;

import talkevent.domain.Talk;

public interface TalkService {

	Talk createTalk(String topic);

	List<Talk> list();

}

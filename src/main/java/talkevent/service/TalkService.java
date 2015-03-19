package talkevent.service;

import java.util.List;

import talkevent.domain.Talk;

public interface TalkService {

	Talk create(Talk talk);

	List<Talk> list();

}

package talkevent.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talkevent.domain.Talk;
import talkevent.domain.TalkDao;

@Service
public class TalkServiceImpl implements TalkService {
	
	private TalkDao talkDao;

	@Autowired
	public TalkServiceImpl(TalkDao talkDao) {
		this.talkDao = talkDao;
	}

	@Transactional
	@Override
	public Talk createTalk(String topic) {
		Talk t = Talk.create(topic);
		t.setId(UUID.randomUUID().toString());
		return talkDao.save(t);
	}

	@Transactional
	@Override
	public List<Talk> list() {
		return talkDao.findAll();
	}

}

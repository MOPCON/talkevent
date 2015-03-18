package talkevent.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import talkevent.domain.Talk;
import talkevent.service.TalkService;

@RestController
public class TalkController {

	private TalkService talkService;

	@Autowired
	public TalkController(TalkService talkService) {
		this.talkService = talkService;
	}
	
    @RequestMapping(value = "/talk", method = RequestMethod.POST)
	public Talk createTalk(@RequestBody @Valid Talk talk) {
		return talkService.create(talk);
	}
	
}

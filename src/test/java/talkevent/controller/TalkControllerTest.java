package talkevent.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import talkevent.controller.TalkController;
import talkevent.controller.param.TalkVO;
import talkevent.domain.Talk;
import talkevent.service.TalkService;

@RunWith(MockitoJUnitRunner.class)
public class TalkControllerTest {

	@Mock
	private TalkService talkService;

	private TalkController controller;

	@Before
	public void setUp() {
		controller = new TalkController(talkService);
	}

	@Test
	public void shouldCreateTalk() throws Exception {
		final Talk savedTalk = Talk.create("");
		when(talkService.createTalk(any(String.class))).thenReturn(savedTalk);

		TalkVO talk = new TalkVO();
		Talk returnedTalk = controller.create(talk);

		verify(talkService, times(1)).createTalk(any(String.class));

		assertEquals("Returned Talk should come from the service", savedTalk,
				returnedTalk);
	}

	@Test
	public void listTalks() throws Exception {
		List<Talk> talks = Arrays.asList(Talk.create(""), Talk.create(""));
		when(talkService.list()).thenReturn(talks);

		List<Talk> returnedTalks = controller.list();

		verify(talkService, times(1)).list();

		assertEquals("Returned Talk list should come from the service", talks,
				returnedTalks);
	}

}
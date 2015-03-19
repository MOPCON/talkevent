package talkevent.service;

import static org.junit.Assert.assertEquals;
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

import talkevent.domain.Talk;
import talkevent.domain.TalkDao;

@RunWith(MockitoJUnitRunner.class)
public class TalkServiceTest {

	@Mock
	private TalkDao talkDao;
	
	private TalkService service;
	
	@Before
	public void setUp() {
		service = new TalkServiceImpl(talkDao);
	}
	
	@Test
    public void shouldCreateTalk() throws Exception {
    	final Talk savedTalk = new Talk();
    	  when(talkDao.save(any(Talk.class))).thenReturn(savedTalk);

    	  final Talk Talk = new Talk();
    	  Talk returnedTalk = service.create(Talk);

    	  verify(talkDao, times(1)).save(Talk);

    	  assertEquals("Returned Talk should come from the dao", savedTalk, returnedTalk);
    }
	
	@Test
	public void listTalks() throws Exception {
		List<Talk> talks = Arrays.asList(new Talk(), new Talk());
		when(talkDao.findAll()).thenReturn(talks);

		List<Talk> returnedTalks = service.list();

		verify(talkDao, times(1)).findAll();

		assertEquals("Returned Talk list should come from the dao", talks,
				returnedTalks);
	}
}

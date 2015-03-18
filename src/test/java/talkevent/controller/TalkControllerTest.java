package talkevent.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import talkevent.controller.TalkController;
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
    	final Talk savedTalk = new Talk();
    	  when(talkService.create(any(Talk.class))).thenReturn(savedTalk);

    	  final Talk Talk = new Talk();
    	  Talk returnedTalk = controller.createTalk(Talk);

    	  verify(talkService, times(1)).create(Talk);

    	  assertEquals("Returned Talk should come from the service", savedTalk, returnedTalk);
    }

}
package talkevent.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkDao extends JpaRepository<Talk, String> {

//	default Talk save(String topic) {
//		Talk t = Talk.create(topic);
//		t.setId(UUID.randomUUID().toString());
//		return this.save(t);
//	}

}

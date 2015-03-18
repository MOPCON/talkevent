package talkevent.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkDao extends JpaRepository<Talk, String> {


}

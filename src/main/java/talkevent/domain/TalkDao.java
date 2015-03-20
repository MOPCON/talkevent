package talkevent.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "talk", path = "talk")
public interface TalkDao extends JpaRepository<Talk, String> {

}

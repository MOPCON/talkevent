package talkevent.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

//@RepositoryRestResource(collectionResourceRel = "talk", path = "talk")
public interface TalkDao extends PagingAndSortingRepository<Talk, Long> {

}

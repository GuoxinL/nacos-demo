package pub.guoxin.demo.gateway.dr.dis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagPathRelationship;

@Repository
public interface TagPathRelationshipRepository extends CrudRepository<TagPathRelationship, Long> {
}

package pub.guoxin.demo.gateway.dr.dis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagComponentRelationship;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagPathRelationship;

import java.util.Collection;
import java.util.List;

@Repository
public interface TagComponentRelationshipRepository extends CrudRepository<TagComponentRelationship, Long> {
    List<TagComponentRelationship> findByTagIn(List<String> tags);
}

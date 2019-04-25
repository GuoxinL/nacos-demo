package pub.guoxin.demo.gateway.dr.dis.route;

import pub.guoxin.demo.gateway.dr.dis.model.tag.TagComponentRelationship;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagPathRelationship;

import java.util.List;

public interface PathComponentRouteRelationshipService {

    void createRelation(List<TagPathRelationship> tagPathRelationship);

    void createRelation(TagComponentRelationship tagComponentRelationship);
}

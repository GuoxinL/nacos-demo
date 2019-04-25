package pub.guoxin.demo.gateway.dr.dis.route.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagComponentRelationship;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagPathRelationship;
import pub.guoxin.demo.gateway.dr.dis.repository.TagComponentRelationshipRepository;
import pub.guoxin.demo.gateway.dr.dis.repository.TagPathRelationshipRepository;
import pub.guoxin.demo.gateway.dr.dis.route.DynamicRouteService;
import pub.guoxin.demo.gateway.dr.dis.route.PathComponentRouteRelationshipService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by guoxin on 2019-04-24
 */
@Service
public class PathComponentRouteRelationshipServiceImpl implements PathComponentRouteRelationshipService {

    @Autowired
    private TagComponentRelationshipRepository tagComponentRelationshipRepository;
    @Autowired
    private TagPathRelationshipRepository      tagPathRelationshipRepository;
    @Autowired
    public  DynamicRouteService                dynamicRouteService;

    @Override
    public void createRelation(List<TagPathRelationship> tagPathRelationship) {
        List<String> collect = tagPathRelationship.stream().map(TagPathRelationship::getTag).collect(Collectors.toList());
        List<TagComponentRelationship> tagComponentRelationships = tagComponentRelationshipRepository.findByTagIn(collect);
        tagComponentRelationships.stream().map(tagComponentRelationship -> {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId();
        })

        dynamicRouteService.add()
    }

    @Override
    public void createRelation(TagComponentRelationship tagComponentRelationship) {

    }
}

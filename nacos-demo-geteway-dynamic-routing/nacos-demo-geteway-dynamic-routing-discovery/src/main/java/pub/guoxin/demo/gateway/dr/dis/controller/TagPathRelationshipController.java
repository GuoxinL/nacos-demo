package pub.guoxin.demo.gateway.dr.dis.controller;

import com.sun.org.apache.xpath.internal.axes.PathComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagPathRelationship;
import pub.guoxin.demo.gateway.dr.dis.repository.TagPathRelationshipRepository;
import pub.guoxin.demo.gateway.dr.dis.route.DynamicRouteService;
import pub.guoxin.demo.gateway.dr.dis.route.PathComponentRouteRelationshipService;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by guoxin on 2019-04-16
 */
@RequestMapping("/gateway/tags")
@RestController
public class TagPathRelationshipController {

    @Autowired
    private DynamicRouteService                   dynamicRouteService;
    @Autowired
    private TagPathRelationshipRepository         tagPathRelationshipRepository;
    @Autowired
    private PathComponentRouteRelationshipService pathComponentRouteRelationshipService;

    @PostMapping
    public String add(@RequestBody List<TagPathRelationship> tagPathRelationships) {
        if (validateTagPathRelationship(tagPathRelationships)) {
            return "400";
        }
        tagPathRelationshipRepository.saveAll(tagPathRelationships);
        pathComponentRouteRelationshipService.createRelation(tagPathRelationships);
        return "200";
    }

    /**
     * 如果有重复则为{@code false}否则为{@code true}
     *
     * @param tagPathRelationships tag
     * @return tagPathRelationships 是否没有重复
     */
    private boolean validateTagPathRelationship(List<TagPathRelationship> tagPathRelationships) {
        return tagPathRelationships.stream().map(TagPathRelationship::getTag).collect(Collectors.toSet()).size() != tagPathRelationships.size();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody List<TagPathRelationship> tagPathRelationships) {
//        RouteDefinition      routeDefinition = assemble(gatewayRouteDefinition);
//        Mono<ResponseEntity> result          = dynamicRouteService.update(routeDefinition);
//        return result.block();
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Mono<ResponseEntity<Object>> result = this.dynamicRouteService.delete(id);
        return result.block();
    }

}

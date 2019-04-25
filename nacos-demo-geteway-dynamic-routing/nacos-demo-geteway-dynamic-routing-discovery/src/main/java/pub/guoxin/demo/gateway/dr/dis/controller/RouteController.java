package pub.guoxin.demo.gateway.dr.dis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.guoxin.demo.gateway.dr.dis.model.GatewayFilterDefinition;
import pub.guoxin.demo.gateway.dr.dis.model.GatewayPredicateDefinition;
import pub.guoxin.demo.gateway.dr.dis.model.GatewayRouteDefinition;
import pub.guoxin.demo.gateway.dr.dis.route.DynamicRouteService;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by guoxin on 2019-04-16
 */
@RequestMapping("/route")
@RestController
public class RouteController {

    @Autowired
    public DynamicRouteService dynamicRouteService;

    @PostMapping
    public ResponseEntity add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition      routeDefinition = assemble(gatewayRouteDefinition);
        Mono<ResponseEntity> result          = dynamicRouteService.add(routeDefinition);
        return result.block();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition      routeDefinition = assemble(gatewayRouteDefinition);
        Mono<ResponseEntity> result          = dynamicRouteService.update(routeDefinition);
        return result.block();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Mono<ResponseEntity<Object>> result = this.dynamicRouteService.delete(id);
        return result.block();
    }

    private RouteDefinition assemble(GatewayRouteDefinition gatewayRouteDefinition) {
        String                           id         = gatewayRouteDefinition.getId();
        String                           uri        = gatewayRouteDefinition.getUri();
        int                              order      = gatewayRouteDefinition.getOrder();
        List<GatewayFilterDefinition>    filters    = gatewayRouteDefinition.getFilters();
        List<GatewayPredicateDefinition> predicates = gatewayRouteDefinition.getPredicates();

        List<FilterDefinition> filterDefinitions = filters.stream().map(gatewayFilterDefinition -> {
            String              name = gatewayFilterDefinition.getName();
            Map<String, String> args = gatewayFilterDefinition.getArgs();

            FilterDefinition filterDefinition = new FilterDefinition();
            filterDefinition.setName(name);
            filterDefinition.setArgs(args);
            return filterDefinition;
        }).collect(Collectors.toList());

        List<PredicateDefinition> predicateDefinitions = predicates.stream().map(gatewayPredicateDefinition -> {
            String              name = gatewayPredicateDefinition.getName();
            Map<String, String> args = gatewayPredicateDefinition.getArgs();

            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setName(name);
            predicateDefinition.setArgs(args);
            return predicateDefinition;
        }).collect(Collectors.toList());

        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(id);
        routeDefinition.setUri(URI.create(uri));
        routeDefinition.setOrder(order);
        routeDefinition.setFilters(filterDefinitions);
        routeDefinition.setPredicates(predicateDefinitions);

        return routeDefinition;
    }
}

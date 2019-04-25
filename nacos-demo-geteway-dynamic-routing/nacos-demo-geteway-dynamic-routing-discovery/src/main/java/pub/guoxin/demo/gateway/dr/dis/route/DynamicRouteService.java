package pub.guoxin.demo.gateway.dr.dis.route;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface DynamicRouteService {

    Mono<ResponseEntity> add(RouteDefinition definition);

    Mono<ResponseEntity> update(RouteDefinition definition);

    Mono<ResponseEntity<Object>> delete(String id);
}

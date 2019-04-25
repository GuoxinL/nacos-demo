package pub.guoxin.demo.gateway.dr.dis.route.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pub.guoxin.demo.gateway.dr.dis.route.DynamicRouteService;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

/**
 * TODO {@link ApplicationEventPublisherAware}写完回来看下这个
 * <p>
 * Create by guoxin on 2019-04-16
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware, DynamicRouteService {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Override
    public Mono<ResponseEntity> add(RouteDefinition definition) {
        // TODO 写完demo回来学习一下reactor这个包
        Disposable subscribe = routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.just(ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity> update(RouteDefinition definition) {
        routeDefinitionWriter.delete(Mono.just(definition.getId()));
        return add(definition);
    }

    @Override
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id))
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }
}

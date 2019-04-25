package pub.guoxin.demo.gateway.dr.dis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author lengleng
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        RouteLocator build = builder.routes()
//                .route(r -> r.path("/test/prefix/**")
//                        .filters(f -> f.stripPrefix(2)
//                                .filter(new CustomFilter())
//                                .addResponseHeader("X-Response-test", "test"))
//                        .uri("lb://SC-CONSUMER")
//                        .order(0)
//                        .id("test_consumer_service")
//                )
//                .build();
//        return build;
//    }

}

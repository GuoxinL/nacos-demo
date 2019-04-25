package pub.guoxin.demo.gateway.dr.prvd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Foo {
    String value();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Foo("test")
@interface Bar {
}

/**
 * Create by guoxin on 2019-04-18
 */
@Configuration
public class GatewayTagsHandlerMappingConfiguraction {

    public static void main(String[] args) {
        Stream<String> collect  = Arrays.stream(((String[]) null));
        Set<String>    collect1 = collect.collect(Collectors.toSet());
        System.out.println(collect1);
    }

    @Bean
    public RequestMappingHandlerMapping gatewayTagsRequestMappingHandlerMapping() {
        GatewayTagsRequestMappingHandlerMapping mappingHandlerMapping = new GatewayTagsRequestMappingHandlerMapping();
        mappingHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);//设置排序
        return mappingHandlerMapping;
    }

}

@Bar
class C {
    public static void main(String... args) throws NoSuchFieldException, IllegalAccessException {
        Bar               bar     = C.class.getAnnotation(Bar.class);
        InvocationHandler handler = Proxy.getInvocationHandler(bar);
        Field             field   = handler.getClass().getDeclaredField("type");
        field.setAccessible(true);
        Class<?> clazz = (Class<?>) field.get(handler);
        if (clazz.isAnnotationPresent(Foo.class)) {
            Foo foo = clazz.getAnnotation(Foo.class);
            System.out.println(foo.value()); // "test"
        }
    }
}

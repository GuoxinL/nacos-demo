package pub.guoxin.demo.gateway.dr.prvd;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import pub.guoxin.demo.gateway.dr.prvd.anno.GatewayTags;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by guoxin on 2019-04-17
 */
@Slf4j
public class GatewayTagsRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        log.info("handlerType: {}, method: {}", handlerType, method);
        Set<String> tags = Sets.newHashSet();

        GatewayTags typeAnnotation = handlerType.getAnnotation(GatewayTags.class);
        tags.addAll(getGatewayTagsValue(typeAnnotation));
        GatewayTags methodAnnotation = method.getAnnotation(GatewayTags.class);
        tags.addAll(getGatewayTagsValue(methodAnnotation));


        RequestMappingInfo mappingForMethod = super.getMappingForMethod(method, handlerType);
        System.out.println(method);
        System.out.println(handlerType);
        if (Objects.nonNull(mappingForMethod)) {
            System.out.println(mappingForMethod.toString());
        } else {
            System.out.println(mappingForMethod);
        }
        log.info("tags: {}", tags);
        return mappingForMethod;
    }
//    private RequestMappingInfo createCustomRequestMappingInfo(Method method) {
//        GatewayTags mapping = AnnotatedElementUtils.findMergedAnnotation(method, GatewayTags.class);
//        if (mapping != null) {
//            RequestMappingInfo.
//            return RequestMappingInfo.paths(Samples.URI_PREFIX_OF_API + mapping.value())
//                    .methods(mapping.method())
//                    .build();
//        }
//        return null;
//    }

    private Set<String> getGatewayTagsValue(GatewayTags methodAnnotation) {
        if (Objects.nonNull(methodAnnotation) && !ArrayUtils.isEmpty(methodAnnotation.value())) {
            return Arrays.stream(methodAnnotation.value()).collect(Collectors.toSet());
        }
        return Sets.newHashSet();
    }

}

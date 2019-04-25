package pub.guoxin.demo.gateway.dr.prvd.anno;

import java.lang.annotation.*;

/**
 * Create by guoxin on 2019-04-17
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ApplicationGatewayTags {

    /**
     * 该参数用于标记Api接口的角色
     *
     * @return tag
     */
    String[] value();

}

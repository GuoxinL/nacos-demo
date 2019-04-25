package pub.guoxin.demo.gateway.dr.dis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由断言定义模型
 */
@Getter
@Setter
public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String              name;
    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}

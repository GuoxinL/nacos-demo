package pub.guoxin.demo.gateway.dr.dis.model.tag;

import lombok.Data;

/**
 * Create by guoxin on 2019-04-23
 */
@Data
public class TagPathRelationship {
    /**
     * 与组件上tag映射
     */
    private String   tag;
    /**
     * 请求路径路径
     */
    private String[] path;
    /**
     * 实例Id
     */
    private String   instanceId;
}

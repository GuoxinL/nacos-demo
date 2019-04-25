package pub.guoxin.demo.gateway.dr.dis.model.tag;

import lombok.Data;

import java.util.List;

/**
 * Create by guoxin on 2019-04-24
 */
@Data
public class TagComponentRelationship {
    /**
     * 与路径上的tag映射
     */
    private String       tag;
    /**
     * 组件名称或Id
     */
    private List<String> componentName;
    /**
     * 描述
     */
    private String       description;
}

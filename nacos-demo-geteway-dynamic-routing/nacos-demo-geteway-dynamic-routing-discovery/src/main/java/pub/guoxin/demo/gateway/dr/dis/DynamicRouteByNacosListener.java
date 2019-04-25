package pub.guoxin.demo.gateway.dr.dis;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pub.guoxin.demo.gateway.dr.dis.route.DynamicRouteService;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Create by guoxin on 2019-04-16
 */
@Component
public class DynamicRouteByNacosListener {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    public DynamicRouteByNacosListener() {
        dynamicRouteByNacosListener("nacos-gateway-provider-id", "gateway-group");
    }

    /**
     * 监听Nacos Server下发的动态路由配置
     *
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener(String dataId, String group) {
        try {
//            Properties properties = new Properties();
//            properties.put("nacos.server-addr", "");
//            properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
//            ConfigService configService=NacosFactory.createConfigService(properties);

            ConfigService configService = NacosFactory.createConfigService("127.0.0.1:8848");
            String        content       = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    RouteDefinition definition = JSON.parseObject(configInfo, RouteDefinition.class);
                    Mono<ResponseEntity> update = dynamicRouteService.update(definition);
                    System.out.println(Objects.requireNonNull(update.block()).toString());
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}

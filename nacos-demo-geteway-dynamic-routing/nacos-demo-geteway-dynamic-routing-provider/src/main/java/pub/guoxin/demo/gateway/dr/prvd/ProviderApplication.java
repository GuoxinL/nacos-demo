package pub.guoxin.demo.gateway.dr.prvd;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import pub.guoxin.demo.gateway.dr.prvd.anno.GatewayTags;

/**
 * @author guoxin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    @GatewayTags({"DS_ONL","DS_WWW"})
    @RequestMapping("/provider")
    @RestController
    class EchoController {

        @GatewayTags({"AIRSPACE_BOSS","AIRSPACE_WWW"})
        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string, Integer abc, Double d) {
            return "hello Nacos Discovery " + string;
        }

        @RequestMapping(value = "/divide", method = RequestMethod.GET)
        public String divide(@RequestParam Integer a, @RequestParam Integer b, Integer abc, Double d) throws NacosException {
            NamingService naming = NamingFactory.createNamingService(System.getProperty("serveAddr"));
            System.out.println(naming.getAllInstances("nacos.test.3"));
            return String.valueOf(a / b);
        }
    }

}


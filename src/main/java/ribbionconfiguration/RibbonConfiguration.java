package ribbionconfiguration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {
// 随机负载均衡
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}

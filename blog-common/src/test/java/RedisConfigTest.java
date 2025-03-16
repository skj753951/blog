import com.kfc.config.RedisConfig;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RedisConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(RedisConfig.class);

        RedissonClient client = context.getBean(RedissonClient.class);
        System.out.println("Redis连接状态：" +
                client.getNodesGroup().pingAll());

        context.close();
    }
}
package wisebite.wisebite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class WiseBiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiseBiteApplication.class, args);
    }

}

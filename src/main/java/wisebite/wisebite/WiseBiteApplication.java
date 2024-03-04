package wisebite.wisebite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import wisebite.wisebite.dto.ClientDTO;

@EnableWebMvc
@SpringBootApplication
public class WiseBiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiseBiteApplication.class, args);
    }

}

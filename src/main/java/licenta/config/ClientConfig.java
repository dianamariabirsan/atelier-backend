package licenta.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"licenta.repository", "licenta.service", "licenta.controller"})
public class ClientConfig {


}
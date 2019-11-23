package demo.project.SimpleApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan("demo.project.SimpleApp")
@EnableJpaRepositories("demo.project.SimpleApp.data.repository")
public class WebConfig implements WebMvcConfigurer {

}

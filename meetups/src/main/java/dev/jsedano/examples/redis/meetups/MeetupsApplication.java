package dev.jsedano.examples.redis.meetups;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import dev.jsedano.examples.redis.meetups.util.InfoLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "dev.jsedano.examples.redis.meetups.*")
public class MeetupsApplication {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  CommandLineRunner loadTestData(InfoLoader infoLoader) {
    return args -> {
      infoLoader.deleteAll();
      infoLoader.load();
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(MeetupsApplication.class, args);
  }
}

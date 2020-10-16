package cn.xwplay.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTestApplication.class, args);
  }

}

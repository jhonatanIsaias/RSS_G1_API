package br.edu.ifs.rss_g1.notices_g1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class RssG1Application {

    public static void main(String[] args) {
        SpringApplication.run(RssG1Application.class, args);
    }

}

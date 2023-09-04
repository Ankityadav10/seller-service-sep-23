package com.cbt.sellerservicesep23;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Sellerservicesep23Application {

    public static void main(String[] args) {


        new SpringApplicationBuilder()
                .profiles("dev")
                .sources(Sellerservicesep23Application.class)
                .run(args);

    }

}

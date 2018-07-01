package com.winfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeixinApplication/* extends SpringBootServletInitializer*/ {

    public static void main(String[] args) {
        SpringApplication.run(WeixinApplication.class, args);
    }

/*	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WeixinApplication.class);
	}*/
}

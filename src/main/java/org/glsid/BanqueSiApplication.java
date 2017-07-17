package org.glsid;


import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@EnableJSONDoc
public class BanqueSiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueSiApplication.class, args);
	}
}

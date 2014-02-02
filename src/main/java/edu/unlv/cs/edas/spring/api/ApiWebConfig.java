package edu.unlv.cs.edas.spring.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"edu.unlv.cs.edas.graph.design.controller.api"})
public class ApiWebConfig extends WebMvcConfigurerAdapter {

	
	
}

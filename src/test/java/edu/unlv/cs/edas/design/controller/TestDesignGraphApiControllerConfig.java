package edu.unlv.cs.edas.design.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import edu.unlv.cs.edas.design.controller.DesignGraphDetailsApiController;
import edu.unlv.cs.edas.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;
import edu.unlv.cs.edas.design.manager.impl.DesignGraphDetailsManagerImpl;

@Configuration
@EnableWebMvc
public class TestDesignGraphApiControllerConfig {

	@Bean
	public DesignGraphDetailsApiController getController() {
		return new DesignGraphDetailsApiController();
	}
	
	@Bean
	public DesignGraphDetailsManager getManager() {
		return new DesignGraphDetailsManagerImpl();
	}
	
	@Bean
	public DesignGraphDomAdapter getDomAdapter() {
		return new DesignGraphDomAdapter();
	}
	
}

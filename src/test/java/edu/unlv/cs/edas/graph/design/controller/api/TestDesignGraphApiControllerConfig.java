package edu.unlv.cs.edas.graph.design.controller.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import edu.unlv.cs.edas.graph.design.adapter.DesignGraphAdapter;
import edu.unlv.cs.edas.graph.design.adapter.impl.DesignGraphAdapterImpl;
import edu.unlv.cs.edas.graph.design.manager.DesignGraphManager;
import edu.unlv.cs.edas.graph.design.manager.impl.DesignGraphManagerImpl;

@Configuration
@EnableWebMvc
public class TestDesignGraphApiControllerConfig {

	@Bean
	public DesignGraphApiController getController() {
		return new DesignGraphApiController();
	}
	
	@Bean
	public DesignGraphAdapter getAdapter() {
		return new DesignGraphAdapterImpl();
	}
	
	@Bean
	public DesignGraphManager getManager() {
		return new DesignGraphManagerImpl();
	}
	
}

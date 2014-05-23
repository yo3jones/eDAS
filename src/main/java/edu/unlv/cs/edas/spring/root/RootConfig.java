package edu.unlv.cs.edas.spring.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import edu.unlv.cs.edas.design.dom.DesignDomGraphBuilder;
import edu.unlv.cs.edas.design.dom.DesignGraphDomAdapter;

@Configuration
@ComponentScan(basePackages="edu.unlv.cs.edas", 
	excludeFilters=@Filter({Controller.class, RestController.class, Configuration.class}))
public class RootConfig {

	@Bean
	DesignGraphDomAdapter getDesignGraphDomAdapter() {
		DesignGraphDomAdapter adapter = new DesignGraphDomAdapter();
		adapter.setBuilder(new DesignDomGraphBuilder());
		return adapter;
	}
	
}

package edu.unlv.cs.edas.spring.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import edu.unlv.cs.edas.design.dom.DesignDomGraphBuilder;
import edu.unlv.cs.edas.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.execute.dom.ExecutionDomGraphBuilder;
import edu.unlv.cs.edas.execute.dom.ExecutionGraphDomAdapter;

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
	
	@Bean
	ExecutionGraphDomAdapter getExecutionGraphDomAdapter() {
		ExecutionGraphDomAdapter adapter = new ExecutionGraphDomAdapter();
		adapter.setBuilder(new ExecutionDomGraphBuilder());
		return adapter;
	}
	
}

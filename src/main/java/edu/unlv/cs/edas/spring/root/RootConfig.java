package edu.unlv.cs.edas.spring.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan(basePackages="edu.unlv.cs.edas", 
	excludeFilters=@Filter({Controller.class, RestController.class, Configuration.class}))
public class RootConfig {

}

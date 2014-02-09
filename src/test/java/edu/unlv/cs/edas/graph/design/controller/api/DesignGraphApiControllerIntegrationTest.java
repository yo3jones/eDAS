package edu.unlv.cs.edas.graph.design.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.InputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestDesignGraphApiControllerConfig.class)
public class DesignGraphApiControllerIntegrationTest {

	@Inject
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void test() throws Exception {
		InputStream in = getClass().getClassLoader().getResourceAsStream("design-graph-sample.txt");
		String jsonGraph = IOUtils.toString(in);
		
		mockMvc.perform(post("/v1/design/graphs")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonGraph))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1001));
		
		mockMvc.perform(get("/v1/design/graphs/1001"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.vertices[0].id.name").value("A"))
			.andExpect(jsonPath("$.vertices[0].position.x").value(1))
			.andExpect(jsonPath("$.vertices[0].position.y").value(1))
			.andExpect(jsonPath("$.vertices[1].id.name").value("B"))
			.andExpect(jsonPath("$.vertices[1].position.x").value(2))
			.andExpect(jsonPath("$.vertices[1].position.y").value(2))
			.andExpect(jsonPath("$.edges[0].id.fromKey.name").value("A"))
			.andExpect(jsonPath("$.edges[0].id.toKey.name").value("B"));
	}
	
}

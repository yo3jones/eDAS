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
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1001));
		
		mockMvc.perform(put("/v1/design/graphs/1001")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonGraph))
			.andExpect(status().isOk());
		
		mockMvc.perform(get("/v1/design/graphs/1001"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.vertices['1'].label").value("A"))
			.andExpect(jsonPath("$.vertices['1'].x").value(150))
			.andExpect(jsonPath("$.vertices['1'].y").value(150))
			.andExpect(jsonPath("$.vertices['2'].label").value("B"))
			.andExpect(jsonPath("$.vertices['2'].x").value(350))
			.andExpect(jsonPath("$.vertices['2'].y").value(150))
			.andExpect(jsonPath("$.edges['1-2'].weight").value(1));
	}
	
}

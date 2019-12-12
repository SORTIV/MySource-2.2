package com.apporchid.solution.samples.tdd;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amwater.mysource.resource.CustomRestController;
import com.apporchid.solution.common.BaseSolutionTestApplication;

public class CustomRestControllerTest extends BaseSolutionTestApplication{

	private MockMvc restMockMvc;
	
	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		CustomRestController customRestController = new CustomRestController();
		
		this.restMockMvc = MockMvcBuilders.standaloneSetup(customRestController).build();
	}
	
	@Test(priority=1)
	public void testCustomRest() throws Exception {
		restMockMvc.perform(get("/api/customrest")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().string("Test"));
	}

	@Override
	public void setupSolution() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getSolutionPackage() {
		return "com.apporchid.solution.samples";
	}
}

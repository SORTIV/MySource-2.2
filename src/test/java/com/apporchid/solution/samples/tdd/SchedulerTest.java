package com.apporchid.solution.samples.tdd;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amwater.mysource.constants.IMySourceCommonConstants;
import com.apporchid.common.utils.ContextHelper;
import com.apporchid.scheduler.JobDefinition;
import com.apporchid.scheduler.JobDefinition.JobStatus;
import com.apporchid.security.rest.client.OAuth2RestClient;
import com.apporchid.solution.common.BaseSolutionTestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SchedulerTest extends BaseSolutionTestApplication {
	@Inject
	protected OAuth2RestClient restClient;
	
	@Test(dataProvider = "jobDef", priority = 1)
	public void testCreateJob(JobDefinition jobDef) throws Exception {
	
		restClient = ContextHelper.getBean(OAuth2RestClient.class);
		ResponseEntity<?> response = restClient.postForEntity(getAbsoluteApiUrl("/api/scheduler/jobs/add"), jobDef, Void.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@DataProvider(name = "jobDef")
	public Object[][] activeJobData() {
		JobDefinition job = new JobDefinition();
		Properties properties = new Properties();
		properties.put("pipelinename", "PostgresSourceToCsvSink");
		properties.put("domain_id", IMySourceCommonConstants.DEFAULT_DOMAIN_ID);
		properties.put("sub_domain_id", IMySourceCommonConstants.DEFAULT_SUB_DOMAIN_ID);
		job.setName("Job12345"); 
		job.setGroupName("JOB_GROUP");
		job.setDescription("job1 description");
		job.setJobClass("com.apporchid.solution.samples.tdd.Job1");
		job.setCronExpression("0 0/2 * * * ?");
		job.setStatus(JobStatus.ACTIVE.name());
		job.setTriggernow(true);
		job.setJobParameters(properties);
		return new Object[][] { { job } };
	}
	
    
    public static String convertObjectToJsonString(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

	@Override
	public void setupSolution() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getSolutionPackage() {
		return "com.amwater.solution.samples";
	}
  
}

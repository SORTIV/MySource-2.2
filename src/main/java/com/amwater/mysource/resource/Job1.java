package com.amwater.mysource.resource;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apporchid.core.common.cache.PipelineCache;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.scheduler.AoServerCoreJob;

public class Job1 extends AoServerCoreJob {

	
	protected final Logger log = LoggerFactory.getLogger(Job1.class);
	
	@Override 
	public void handleExecute(JobExecutionContext jec) throws Exception {

		IPipeline pipeline = PipelineCache.INSTANCE.get(getJobParameter(jec, "domain_id"),getJobParameter(jec, "sub_domain_id"),getJobParameter(jec, "pipelinename"));
		pipeline.run();
		log.info("job execution started jobname is " + jec.getJobDetail().getKey().getName());
		log.info("job class is " + jec.getJobDetail().getJobClass());
		log.info("job has executed successfully");
		
	}

}

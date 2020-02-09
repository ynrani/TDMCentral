package com.tdm.schedulers;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tdm.controller.TdmSpringScheduleController;

public class RunMeJob extends QuartzJobBean {
	private TdmSpringScheduleController runMeTask;

	public void setRunMeTask(TdmSpringScheduleController runMeTask) {
		this.runMeTask = runMeTask;
	}

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		runMeTask.printMe();

	}
}
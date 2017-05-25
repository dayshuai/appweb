package com.ssm.app;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScheduledJob extends QuartzJobBean{

	private TwoBean twoBean;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		twoBean.outMessage();
	}

	public void setTwoBean(TwoBean twoBean) {
		this.twoBean = twoBean;
	}
}
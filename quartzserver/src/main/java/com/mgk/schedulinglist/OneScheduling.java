package com.mgk.schedulinglist;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service
public class OneScheduling {
	private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	@Scheduled(fixedRateString = "5000",initialDelay = 3000)
	public void testTask1() {
		System.out.println("fixedRateString,当前时间：" +format.format(new Date()));
	}
}

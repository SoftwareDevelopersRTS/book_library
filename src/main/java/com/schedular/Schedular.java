package com.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedular {

	//@Scheduled(fixedRate = 1000)
	public void printSomethingToTest() {
		System.out.println("Schedular is running----->" + System.currentTimeMillis());
	}

}

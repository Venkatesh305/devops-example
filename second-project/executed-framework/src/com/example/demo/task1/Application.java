package com.example.demo.task1;

import java.util.concurrent.*;

public class Application {
	public static void UsingFixedThreadExecutor() {
		
		int processorcount = Runtime.getRuntime().availableProcessors();
		
		System.out.println(+processorcount);
		ExecutorService service=
				Executors.newFixedThreadPool(4);
		
		UsingPrintString[] tasks = 
			{new UsingPrintString("Idle","sambar"),
			new UsingPrintString("pizza","coke"),
			new UsingPrintString("paratha","achar"),
			new UsingPrintString("Tea","biscuit")
			};
		
		for(UsingPrintString eachTask:tasks) {
			
			service.submit(eachTask);
			
		}
		service.shutdown();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsingFixedThreadExecutor();
	}

}

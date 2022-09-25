package com.example.demo.task2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Callable<Integer> task = new CountNumber(10);
			
			ExecutorService service=
					Executors.newFixedThreadPool(8);
			
			 Future<Integer> response=service.submit(task);
			
			
		
//			System.out.println("Is task completed="+response.isDone());
//			System.out.println("Result:"+response.get());
			
			while(!response.isDone()){
				System.out.println("Waiting to complete");
				
				System.out.println("Result"+response.get());
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.example.demo.task2;


import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CountNumber implements Callable<Integer> {
	
	private int countTo;
	public CountNumber(int CountTo){
		super();
		this.countTo=countTo;
		
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		
		
		int total=0;
		for(int i=0;i<=10;i++) {
			total+=i;
		}
		
		
      TimeUnit.MILLISECONDS.sleep(2);
		return total;
	}
	

}

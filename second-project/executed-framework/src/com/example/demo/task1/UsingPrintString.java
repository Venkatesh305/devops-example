package com.example.demo.task1;

public class UsingPrintString implements Runnable {
	
	private String str1;
	private String str2;
	
	public UsingPrintString(String str1,String str2) {
		super();
		this.str1=str1;
		this.str2=str2;
		
	}

	

	

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		PrintString printString = new PrintString();
		// TODO Auto-generated method stub
		printString.print(str1, str2);

	}

}

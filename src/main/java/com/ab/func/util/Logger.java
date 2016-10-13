package com.ab.func.util;

public class Logger {
    private String name;
    private boolean logit = false;
    public Logger(String name)
    {
        this.name = name;
    }
    public void log(String msg)
    {
    	if(logit)
    		System.out.println(name+":"+msg);
    }
	public void log(Exception e) {
		if(logit)
			e.printStackTrace();
	}
}

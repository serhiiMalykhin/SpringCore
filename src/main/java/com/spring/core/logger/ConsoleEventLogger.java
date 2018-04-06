package com.spring.core.logger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(String msg){
        System.out.println(msg);
    }
}

package com.spring.core;

import com.spring.core.event.Event;
import com.spring.core.logger.EventLogger;
import com.spring.core.model.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event) {
        String handedEvent = event.getMsg();
        event.setMsg(handedEvent.replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean(App.class);
        Event event = context.getBean(Event.class);
        app.logEvent(event);
    }
}

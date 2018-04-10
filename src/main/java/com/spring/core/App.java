package com.spring.core;

import com.spring.core.logger.EventLogger;
import com.spring.core.model.Client;
import com.spring.core.model.Event;
import com.spring.core.model.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private Map<EventType, EventLogger> loggers;
    private EventLogger defaultLogger;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }

        String handedEvent = event.getMsg();
        event.setMsg(handedEvent.replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean(App.class);
        EventType type = context.getBean(EventType.class);
        for (int i = 0; i < 10; i++) {
            Event event = context.getBean(Event.class);
            app.logEvent(type, event);
        }

        context.close();
    }
}

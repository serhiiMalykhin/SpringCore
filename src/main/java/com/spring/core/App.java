package com.spring.core;

import com.spring.core.logger.EventLogger;
import com.spring.core.model.Client;
import com.spring.core.model.Event;
import com.spring.core.model.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
@ComponentScan("com.spring.core")
@ImportResource("classpath:spring.xml")
public class App {

    @Autowired
    private Client client;

    @Resource(name="loggerMap")
    private Map<EventType, EventLogger> loggers;


    @Autowired
    @Qualifier("consoleEventLogger")
    private EventLogger defaultLogger;

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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        App app = context.getBean(App.class);
        EventType type = context.getBean(EventType.class);
        for (int i = 0; i < 10; i++) {
            Event event = context.getBean(Event.class);
            app.logEvent(type, event);
        }
        context.close();
    }
}

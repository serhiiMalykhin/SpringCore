package com.spring.core.logger;

import com.spring.core.model.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {

    private List<EventLogger> loggers;

    public List<EventLogger> getLoggers() {
        return loggers;
    }

    public void setLoggers(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(eventLogger -> eventLogger.logEvent(event));
    }
}

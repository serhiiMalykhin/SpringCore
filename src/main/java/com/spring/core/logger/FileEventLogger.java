package com.spring.core.logger;

import com.spring.core.event.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Collections;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    private void init() throws IOException {
        this.file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.canWrite()) {
            throw new FileSystemException("File cannot write");
        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeLines(file, Collections.singleton(event), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }
}

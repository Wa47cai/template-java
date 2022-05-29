package org.oobootcamp.dip;

import java.io.StringWriter;

public class SimpleLogger {
    public SimpleLogger(FileAppender fileAppender) {
        this.fileAppender = fileAppender;
    }

    private FileAppender fileAppender;

    public void log(String message) {
        fileAppender.append(message);
    }

    static void main(String[] args){

    }

}

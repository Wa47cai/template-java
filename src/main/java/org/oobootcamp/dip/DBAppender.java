package org.oobootcamp.dip;

public class DBAppender implements IAppendable {

    @Override
    public void append(String message) {
        System.out.println(message + "saved to DB");
    }
}

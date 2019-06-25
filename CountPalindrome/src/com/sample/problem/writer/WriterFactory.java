package com.sample.problem.writer;

public class WriterFactory {

    public Writer getWriter(WriterType type) {
        switch (type) {
        case DB_WRITER:
            return new DBWriter();
        default:
            return new ConsoleWriter();
        }
    }

}

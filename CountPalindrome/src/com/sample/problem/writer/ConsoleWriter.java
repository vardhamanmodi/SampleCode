package com.sample.problem.writer;
import java.util.List;
import java.util.Map.Entry;

public class ConsoleWriter implements Writer {

    @Override
    public void write(List<Entry<String, Integer>> records) {
        System.out.println(records);
    }

}

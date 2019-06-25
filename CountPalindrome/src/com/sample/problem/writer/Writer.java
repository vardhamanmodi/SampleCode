package com.sample.problem.writer;
import java.util.List;
import java.util.Map.Entry;

public interface Writer {
    void write(List<Entry<String, Integer>> records);
}

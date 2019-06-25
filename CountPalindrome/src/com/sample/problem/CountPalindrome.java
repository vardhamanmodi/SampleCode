package com.sample.problem;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sample.problem.writer.Writer;
import com.sample.problem.writer.WriterFactory;
import com.sample.problem.writer.WriterType;

public class CountPalindrome {

    static Map<String, Integer> palindromeCount = new HashMap<>();
    static WriterType WRITER = WriterType.CONSOLE_WRITER;   
    
    /**
     * main method responsible for executing the engine 
     *  
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path file = Paths.get(args[0]);
        Writer writer = getWriter();
        try (Stream<String> lines = Files.lines(file , Charset.defaultCharset())) {
            lines.forEach(l -> {countPalindrome(l); writeSortedMap(writer);});
          }
    }
    
    /**
     * Writes sorted records to the selected writer 
     * 
     * @param writer
     */
    private static void writeSortedMap(Writer writer) {
        List<Entry<String, Integer>> sortedRecords = palindromeCount.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());
        writer.write(sortedRecords);
        
    }

    /**
     * Get Writer based on the writer Type
     * 
     * @return
     */
    private static Writer getWriter() {
        WriterFactory factory = new WriterFactory();
        return factory.getWriter(WRITER);
    }


    /**
     * Responsible to count palindrome words in a line and also takes care of cumulative sum for each line in the file
     * 
     * @param line
     */
    private static void countPalindrome(String line) {
        StringTokenizer st = new StringTokenizer(line, ".?! ");
        while (st.hasMoreElements()) {
            String word = st.nextToken();
            if(isPalindrome(word)) {
                if(palindromeCount.containsKey(word)) {
                    palindromeCount.put(word, palindromeCount.get(word) + 1);
                } else {
                    palindromeCount.put(word, 1);
                }
            }
        }
    }
    
    /**
     * responsible to check if given word is a palindrome or not
     *  
     * @param word
     * @return
     */
    private static boolean isPalindrome(String word) {
        int n = word.length();
        
        //check for single letter words
        if(n < 2)
            return false;

        word = word.toLowerCase(); 
        for (int i=0; i<n; i++,n--) 
           if (word.charAt(i) != word.charAt(n - 1)) 
              return false;        
        return true; 
    }

}

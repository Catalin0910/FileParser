package dataparser.analytics;

import java.util.HashMap;
import java.util.Map;

public class MostUsedWordModule implements AnalyticsModule {
    private String mostUsedWord;

    @Override
    public void analyze(String content) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        String[] words = content.split("\\s+");
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
        mostUsedWord = wordCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    @Override
    public void displayResult() {
        System.out.println("Most used word: " + mostUsedWord + "\n");
    }
}
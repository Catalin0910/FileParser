package dataparser.analytics;

public class WordCountModule implements AnalyticsModule {
    private int wordCount;

    @Override
    public void analyze(String content) {
        wordCount = content.split("\\s+").length;
    }

    @Override
    public void displayResult() {
        System.out.println("Number of words: " + wordCount);
    }

    public int getWordCount() {
        return wordCount;
    }
}
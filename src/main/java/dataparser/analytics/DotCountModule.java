package dataparser.analytics;

public class DotCountModule implements AnalyticsModule {
    private int dotCount;

    @Override
    public void analyze(String content) {
        dotCount = content.length() - content.replace(".", "").length();
    }

    @Override
    public void displayResult() {
        System.out.println("Number of dots: " + dotCount);
    }
}
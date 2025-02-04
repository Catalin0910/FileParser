package dataparser.main;

import dataparser.analytics.AnalyticsModule;
import dataparser.analytics.DotCountModule;
import dataparser.analytics.MostUsedWordModule;
import dataparser.analytics.WordCountModule;
import dataparser.core.DataProcessor;
import dataparser.core.FileWatcher;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String directoryPath = "C:/FileParser/src/test/resources/input";


        List<AnalyticsModule> analyticsModules = Arrays.asList(
                new WordCountModule(),
                new DotCountModule(),
                new MostUsedWordModule()
        );

        DataProcessor dataProcessor = new DataProcessor(analyticsModules);
        FileWatcher fileWatcher = new FileWatcher(directoryPath, dataProcessor);

        try {
            fileWatcher.watch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
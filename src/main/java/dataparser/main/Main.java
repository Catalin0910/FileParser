package dataparser.main;

import dataparser.analytics.*;
import dataparser.core.DataProcessor;
import dataparser.core.FileWatcher;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String directoryPath = Paths.get("src", "test", "resources", "input").toAbsolutePath().toString();

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
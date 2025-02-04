package dataparser.core;

import dataparser.analytics.AnalyticsModule;
import dataparser.factory.FileParserFactory;
import dataparser.parser.FileParser;


import java.io.File;
import java.util.List;

public class DataProcessor {
    private List<AnalyticsModule> analyticsModules;

    public DataProcessor(List<AnalyticsModule> analyticsModules) {
        this.analyticsModules = analyticsModules;
    }

    public void processFile(File file) {
        FileParser parser = FileParserFactory.getParser(file);
        if (parser != null) {
            String content = parser.parse(file);
            for (AnalyticsModule module : analyticsModules) {
                module.analyze(content);
                module.displayResult();
            }
        }
    }
}
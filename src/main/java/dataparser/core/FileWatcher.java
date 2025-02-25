package dataparser.core;

import java.io.File;
import java.nio.file.*;

public class FileWatcher {
    private final String directoryPath;
    private final DataProcessor dataProcessor;

    public FileWatcher(String directoryPath, DataProcessor dataProcessor) {
        if (directoryPath == null || directoryPath.isEmpty()) {
            throw new IllegalArgumentException("Directory path cannot be null or empty.");
        }
        this.directoryPath = directoryPath;
        this.dataProcessor = dataProcessor;
    }

    public void watch() throws Exception {
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Specified path is not a valid directory: " + directoryPath);
        }

        Path processedDir = Paths.get(directoryPath, "processed");
        if (!Files.exists(processedDir)) {
            Files.createDirectories(processedDir);
            System.out.println("Processed directory created.");
        }

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && (file.getName().endsWith(".txt") || file.getName().endsWith(".csv") || file.getName().endsWith(".pdf"))) {
                    System.out.println("Processing file " + file.getName());
                    dataProcessor.processFile(file);
                    FileMover.moveFile(file, directoryPath + "/processed");
                }
            }
        }

        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }
}
package dataparser.core;

import java.io.File;
import java.nio.file.*;

public class FileWatcher {
    private final String directoryPath;
    private final DataProcessor dataProcessor;

    public FileWatcher(String directoryPath, DataProcessor dataProcessor) {
        this.directoryPath = directoryPath;
        this.dataProcessor = dataProcessor;
    }

    public void watch() throws Exception {
        Path path = Paths.get(directoryPath);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Specified path is not a valid directory: " + directoryPath);
        }

        Path processedDir = Paths.get(directoryPath, "processed");
        if (!Files.exists(processedDir)) {
            Files.createDirectories(processedDir);
            System.out.println("processed directory already exist");
        }

        // txt processing
        File directory = new File(directoryPath);
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                System.out.println("Wait for file " + file.getName() + " to be processed");
                dataProcessor.processFile(file);
                FileMover.moveFile(file, directoryPath + "/processed");
            }
        }

        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }
}
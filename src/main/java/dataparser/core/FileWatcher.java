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

        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        System.out.println("Watching directory: " + directoryPath);

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    File newFile = new File(directoryPath, event.context().toString());
                    if (newFile.isFile() && (newFile.getName().endsWith(".txt") ||
                            newFile.getName().endsWith(".csv") ||
                            newFile.getName().endsWith(".pdf"))) {
                        System.out.println("Processing new file: " + newFile.getName());
                        dataProcessor.processFile(newFile);
                        FileMover.moveFile(newFile, processedDir.toString());
                    }
                }
            }
            key.reset();
        }
    }
}

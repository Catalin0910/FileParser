package dataparser.core;

import java.io.File;
import java.io.IOException;
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

    public void watch() throws IOException, InterruptedException {
        Path path = Paths.get(directoryPath);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Invalid directory: " + directoryPath);
        }

        Files.createDirectories(Paths.get(directoryPath, "processed"));
        System.out.println("Watching directory: " + directoryPath);

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            while (true) {
                WatchKey key = watchService.take();
                key.pollEvents().stream()
                        .map(event -> new File(directoryPath, event.context().toString()))
                        .filter(file -> file.isFile() && (file.getName().endsWith(".txt") ||
                                file.getName().endsWith(".csv") ||
                                file.getName().endsWith(".pdf")))
                        .forEach(file -> {
                            System.out.println("Processing new file: " + file.getName());
                            dataProcessor.processFile(file);
                            try {
                                FileMover.moveFile(file, directoryPath + "/processed");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                key.reset();
            }
        }
    }
}

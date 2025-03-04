package dataparser.parser;

import java.io.*;

public class TxtFileParser implements FileParser {
    private static final int BUFFER_SIZE = 8192; // 8KB buffer per read

    @Override
    public String parse(File file) {
        StringWriter writer = new StringWriter();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            char[] buffer = new char[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file.getName(), e);
        }
        return writer.toString();
    }
}

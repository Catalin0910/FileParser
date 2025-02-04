package dataparser.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TxtFileParser implements FileParser {
    @Override
    public String parse(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
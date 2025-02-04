package dataparser.factory;


import dataparser.parser.FileParser;
import dataparser.parser.TxtFileParser;

import java.io.File;

public class FileParserFactory {
    public static FileParser getParser(File file) {
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".txt")) {
            return new TxtFileParser();
        }
        // here we can add new files parsers like csv, pdf, etc
        return null;
    }
}
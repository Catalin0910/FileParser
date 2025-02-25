package dataparser.factory;

import dataparser.parser.FileParser;
import dataparser.parser.PDFFileParser;
import dataparser.parser.CSVFileParser;
import dataparser.parser.TxtFileParser;

import java.io.File;

public class FileParserFactory {
    public static FileParser getParser(File file) {
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".pdf")) {
            return new PDFFileParser();
        } else if (fileName.endsWith(".csv")) {
            return new CSVFileParser();
        } else if (fileName.endsWith(".txt")) {
            return new TxtFileParser();
        }

        throw new IllegalArgumentException("Unsupported file type: " + fileName);
    }
}

package dataparser.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class CSVFileParser implements FileParser {

    @Override
    public String parse(File file) {
        StringBuilder content = new StringBuilder();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                content.append(String.join(",", row)).append("\n");
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error parsing CSV file: " + file.getName(), e);
        }
        return content.toString();
    }
}

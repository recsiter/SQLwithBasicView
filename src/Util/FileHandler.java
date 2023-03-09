package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author G
 */
public class FileHandler {

    private static final String DELIMITER = ",";
    private static final String[] DELIMITER_PATTERN = {DELIMITER, ""};

    private FileHandler() {
    }

    // BEOLVASÁS átalakítás
    public static List<List<String>> readIn(String path) {
        List<List<String>> result = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String rawRow;
            while ((rawRow = reader.readLine()) != null) {
                List<String> nextRow = readRow(rawRow);
                result.add(nextRow);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static List<String> readRow(String rawRow) {
        List<String> result = new ArrayList<>();
        String[] rowArray = rawRow.split(DELIMITER);
        fillList(result, rowArray);
        return result;
    }

    private static void fillList(List<String> result, String[] rowArray) {
        for (String string : rowArray) {
            result.add(string);
        }
    }
// Innentől mentés, fileBA BELEÍRÁS

    public static void writeOut(List<List<String>> rows, String path) {
        String writeable = createWriteable(rows);
        writeToFile(writeable, path);
    }

    private static String createWriteable(List<List<String>> rows) {
        StringBuilder builder = new StringBuilder();
        String[] lineEndPattern = {System.lineSeparator(), ""};
        for (int i = 0; i < rows.size(); i++) {
            addRow(rows.get(i), builder);
            builder.append(lineEndPattern[(i + 1) / rows.size()]);
        }
        return builder.toString();
    }

    private static void addRow(List<String> data, StringBuilder builder) {
        for (int i = 0; i < data.size(); i++) {
            builder.append(data.get(i));
            builder.append(DELIMITER_PATTERN[(i + 1) / data.size()]);

        }
    }

    public static void writeToFile(String writeable, String path) {

        try ( FileWriter writer = new FileWriter(path)) {
            writer.write(writeable);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

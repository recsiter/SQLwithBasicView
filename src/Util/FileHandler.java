package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;

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

    private static void addRow(List<String> data, StringBuilder builder) {
        for (int i = 0; i < data.size(); i++) {
            builder.append(data.get(i));
            builder.append(DELIMITER_PATTERN[(i + 1) / data.size()]);

        }
    }

    public static void writeToFile(String writeable, String path) {

        try ( FileWriter writer = new FileWriter(path, true)) {
            writer.write(writeable);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void createTxtInFolder(String pathToCreate,
            String pathReadFrom, String fileName) {

        createStringFromRead(pathReadFrom);
        File file = new File(pathToCreate + "\\" + fileName + ".txt");
        try {
            Scanner scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            // file was not found
            System.out.println("File not found");
        } catch (Exception e) {

            System.out.println("Error reading file");
        }
        try ( FileWriter writer = new FileWriter(file, true);) {
            writer.write(createStringFromRead(pathReadFrom));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static String createStringFromRead(String pathReadFrom) {
        FileReader reader;
        StringBuilder builder = new StringBuilder();
        try ( BufferedReader br = new BufferedReader(
                new FileReader(pathReadFrom))) {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void clearFile(String path) {

        try {
            FileWriter writer = new FileWriter(path);
            writer.write("");
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }
}

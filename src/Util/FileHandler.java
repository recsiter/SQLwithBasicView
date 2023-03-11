package Util;

import entities.DurableProducts;
import entities.PerishableProducts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author G
 */
public class FileHandler {

    private FileHandler() {
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

    public static StringBuilder quantityChangedBuilderP(
            PerishableProducts product) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        builder.append("Perishable Product amount changed: ");
        builder.append(((PerishableProducts) product).getArticleNumber()).
                append(" ");
        builder.append("new amount: ").
                append(((PerishableProducts) product).getQuantity()).
                append(" ");
        builder.append(now).
                append(" ");
        builder.append(System.lineSeparator());
        return builder;
    }

    public static StringBuilder quantityChangedBuilderD(DurableProducts product) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        builder.append("Durable Product amount changed: ");
        builder.append(((DurableProducts) product).getArticleNumber()).
                append(" ");
        builder.append("new amount: ").
                append(((DurableProducts) product).getQuantity()).
                append("  ");
        builder.append(now).
                append(" ");
        builder.append(System.lineSeparator());
        return builder;
    }

    public static StringBuilder logStringCreatorP(
            PerishableProducts product) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        builder.append("Perishable created: ");
        builder.append(((PerishableProducts) product).getArticleNumber()).
                append(" ");
        builder.append(now).
                append(" ");
        builder.append(System.lineSeparator());
        return builder;
    }

    public static StringBuilder logStringCreatorD(
            DurableProducts product) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        builder.append("Durable created: ");
        builder.append(((DurableProducts) product).getArticleNumber()).
                append(" ");
        builder.append(now).
                append(" ");
        builder.append(System.lineSeparator());
        return builder;
    }
}

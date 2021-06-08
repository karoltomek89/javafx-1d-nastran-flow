package com.kt.javafx.oneDNastranFlow.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Saver {

    public static void saveResult(List<String> test1, List<String> test2, List<String> properties, String bdfName) throws IOException {

        bdfName = bdfName.replace(".bdf", "_plotel.txt");

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(bdfName));

        for (String s : test1) {
            writer.write(s + "\n");
        }
        for (String s : test2) {
            writer.write(s + "\n");
        }
        for (String s : properties) {
            writer.write(s + "\n");
        }
        writer.close();
    }

}

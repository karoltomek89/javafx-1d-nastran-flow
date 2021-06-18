package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Saver {

   static MySingleLogger mySingleLogger = MySingleLogger.getInstance();
   static Logger logger = mySingleLogger.getLogger();

    public static void saveResult(List<String> chbdypList, List<String> convmList, List<String> properties, String bdfName){

        try {
            Objects.requireNonNull(chbdypList);
            Objects.requireNonNull(convmList);
            Objects.requireNonNull(properties);
            Objects.requireNonNull(bdfName);

            logger.info("Saving results...");

            bdfName = bdfName.replace(".bdf", "_CHBDYP_CONVM.txt");

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(bdfName));

            for (String s : chbdypList) {
                writer.write(s + "\n");
            }
            for (String s : convmList) {
                writer.write(s + "\n");
            }
            for (String s : properties) {
                writer.write(s + "\n");
            }
            logger.info("Saving results finished.");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            logger.error("Empty input data!");
        }
    }
}

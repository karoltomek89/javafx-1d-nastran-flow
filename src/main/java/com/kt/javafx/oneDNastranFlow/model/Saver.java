package com.kt.javafx.oneDNastranFlow.model;

import javafx.scene.control.Alert;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Saver {

    static MySingleLogger mySingleLogger = MySingleLogger.getInstance();
    static Logger logger;

    public static void saveResult(List<String> chbdypList, List<String> convmList, List<String> properties, String bdfName, Boolean insertResults, List<String> wholeBDF) {
        logger = mySingleLogger.getLogger();

        try {
            Objects.requireNonNull(chbdypList);
            Objects.requireNonNull(convmList);
            Objects.requireNonNull(properties);
            Objects.requireNonNull(bdfName);

            logger.info("Saving results...");

            String resultFileName = bdfName.replace(".bdf", "_CHBDYP_CONVM.txt");

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(resultFileName));

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

            if (insertResults) {

                BufferedWriter writerExtra = Files.newBufferedWriter(Paths.get(bdfName));

                wholeBDF.remove(wholeBDF.size() - 1);

                for (String s : wholeBDF) {
                    writerExtra.write(s + "\n");
                }
                for (String s : chbdypList) {
                    writerExtra.write(s + "\n");
                }
                for (String s : convmList) {
                    writerExtra.write(s + "\n");
                }
                for (String s : properties) {
                    writerExtra.write(s + "\n");
                }
                writerExtra.write("ENDDATA");
                logger.info("Saving results finished.");

                writerExtra.close();
            }

        } catch (IOException e) {
            logger.error("Error saving results!");
            AlertCreator.createError("Error saving results!", "Check the permissions!");

        } catch (NullPointerException e) {
            logger.error("No data to save!");
            AlertCreator.createError("No data to save!", "Check BDF file and input");
        }
    }
}

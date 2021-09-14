package com.kt.javafx.oneDNastranFlow.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BDFReader {

    private List<String> plotelList;
    private List<String> wholeBDF;
    MySingleLogger mySingleLogger;
    Logger logger;

    public BDFReader() {
        this.plotelList = new ArrayList<>();
        this.mySingleLogger = MySingleLogger.getInstance();
    }

    public List<String> readBDF(String pathToDBF) {
        logger = mySingleLogger.getLogger();

        if (pathToDBF.equals("BDF name")) pathToDBF = null;

        try {
            Objects.requireNonNull(pathToDBF);


            logger.info("Reading file: " + pathToDBF);

            wholeBDF = Files.lines(Paths.get(pathToDBF), StandardCharsets.ISO_8859_1).collect(Collectors.toList());

            plotelList = wholeBDF.parallelStream()
                    .filter(p -> p.startsWith("PLOTEL  "))
                    .sorted()
                    .collect(Collectors.toList());


        } catch (IOException e) {
            logger.error("Error reading file!");
            AlertCreator.createError("Error reading file!","Check the BDF file path!");

        } catch (NullPointerException e) {
            logger.error("Empty path to file!");
            AlertCreator.createError("Empty path to file!", "Check the BDF file path!");

        }


        logger.info("Number of PLOTEL elements in BDF file: " + plotelList.size());

        ResultInformation.numberPlotelElements = plotelList.size();

        return plotelList.parallelStream()
                .map(p -> p.trim()
                        .substring(9, 32))

                .map(s -> s.trim().replaceAll("[ ]{1,}", ","))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getWholeBDF() {
        return wholeBDF;
    }

    public void clear() {
        plotelList = new ArrayList<>();
    }
}

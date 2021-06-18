package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BDFReader {

    private List<String> plotelList;
    MySingleLogger mySingleLogger;
    Logger logger;

    public BDFReader() {
        this.plotelList = new ArrayList<>();
        this.mySingleLogger = MySingleLogger.getInstance();
        this.logger = mySingleLogger.getLogger();
    }

    public List<String> readBDF(String pathToDBF) {

        try {
            Objects.requireNonNull(pathToDBF);

            logger.info("Reading file: " + pathToDBF);

            plotelList = Files.lines(Paths.get(pathToDBF), StandardCharsets.ISO_8859_1)
                    .filter(p -> p.startsWith("PLOTEL  "))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error reading file!");
        } catch (NullPointerException e) {
            logger.error("Empty path to file!");
        }

        logger.info("Number of PLOTEL elements in BDF file: " + plotelList.size());

        return plotelList.parallelStream()
                .map(p -> p.trim()
                        .substring(9, 32))

                .map(s -> s.trim().replaceAll("[ ]{1,}", ","))
                .sorted()
                .collect(Collectors.toList());
    }

    public void clear() {
        plotelList = new ArrayList<>();
    }
}

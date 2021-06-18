package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BDFReader {

    private String bdfName;
    private List<String> plotelList;
    MySingleLogger mySingleLogger;
    Logger logger;

    public BDFReader() {
        this.bdfName = null;
        this.plotelList = new ArrayList<>();
        this.mySingleLogger = MySingleLogger.getInstance();
        this.logger = mySingleLogger.getLogger();
    }

    public List<String> readBDF(String pathToDBF) {

        try {
            logger.info("Reading file: " + pathToDBF);
            bdfName = pathToDBF;

            plotelList = Files.lines(Paths.get(bdfName), StandardCharsets.ISO_8859_1)
                    .filter(p -> p.startsWith("PLOTEL  "))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error reading file!");
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
        bdfName = null;
        plotelList = new ArrayList<>();
    }
}

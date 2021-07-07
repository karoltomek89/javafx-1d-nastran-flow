package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElementsCreator {

    private int startNumber;
    private List<String> chbdypForExport;
    private List<String> convmForExport;
    MySingleLogger mySingleLogger;
    Logger logger;

    public ElementsCreator() {
        this.chbdypForExport = new ArrayList<>();
        this.convmForExport = new ArrayList<>();
        this.mySingleLogger = MySingleLogger.getInstance();
    }

    public List<String> createChbdyp(int offset, int[][] plotelAboveSolid, int propertyId, String surfaceType) {
        logger = mySingleLogger.getLogger();

        try {
            Objects.requireNonNull(surfaceType);

            startNumber = Math.min(plotelAboveSolid[1][0], plotelAboveSolid[plotelAboveSolid.length - 1][0]) + offset;

            logger.info("Number of first CHBDYP element is: " + startNumber);

            ResultInformation.numberFirstChbdyp = startNumber;

            for (int i = 0; i < plotelAboveSolid.length; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("CHBDYP")
                        .append(",")
                        .append(startNumber + i)
                        .append(",")
                        .append(propertyId)
                        .append(",")
                        .append(surfaceType)
                        .append(",,,")
                        .append(plotelAboveSolid[i][1])
                        .append(",")
                        .append(plotelAboveSolid[i][2]);
                chbdypForExport.add(stringBuilder.toString());
            }
        } catch (NullPointerException e) {
            logger.error("No information of surface type!");
        }

        logger.info("Number of CHBDYP elements created: " + chbdypForExport.size());

        ResultInformation.numberChbdypElements = chbdypForExport.size();

        return chbdypForExport;
    }

    public List<String> createConvm(int[][] plotelAboveSolid, int offset, int[][] plotelInSolid, int propertyId,
                                    int ctrlMassId) {
        logger = mySingleLogger.getLogger();

        startNumber = Math.min(plotelAboveSolid[1][0], plotelAboveSolid[plotelAboveSolid.length - 1][0]) + offset;

        logger.info("Number of first CONVM element is: " + startNumber);

        ResultInformation.numberFirstConvm = startNumber;

        for (int i = 0; i < plotelInSolid.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CONVM")
                    .append(",")
                    .append(startNumber + i)
                    .append(",")
                    .append(propertyId)
                    .append(",,")
                    .append(ctrlMassId)
                    .append(",")
                    .append(plotelInSolid[i][1])
                    .append(",")
                    .append(plotelInSolid[i][2]);
            convmForExport.add(stringBuilder.toString());
        }

        logger.info("Number of CONVM elements created: " + convmForExport.size());

        ResultInformation.numberConvmElements = convmForExport.size();

        return convmForExport;
    }
    public void clear(){
        chbdypForExport = new ArrayList<>();
        convmForExport = new ArrayList<>();
    }
}

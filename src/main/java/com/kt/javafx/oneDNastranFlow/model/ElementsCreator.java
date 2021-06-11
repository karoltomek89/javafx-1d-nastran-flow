package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ElementsCreator {

   public static List<String> createChbdyp(int offset, int[][] plotelAboveSolid, int propertyId, String surfaceType,
                                           Logger logger) {

       logger.log(Level.DEBUG, "creating CHBDYP elements started");

        List<String> chbdypForExport = new ArrayList<>();
        int startNumber = Math.min(plotelAboveSolid[1][0], plotelAboveSolid[plotelAboveSolid.length - 1][0]) + offset;

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

       logger.log(Level.DEBUG, "creating CHBDYP elements finished");

        return chbdypForExport;
    }

    public static List<String> createConvm(int[][] plotelAboveSolid, int offset, int[][] plotelInSolid, int propertyId,
                                           int ctrlMassId, Logger logger) {

        logger.log(Level.DEBUG, "creating CONVM elements started");

        List<String> convmForExport = new ArrayList<>();
        int startNumber = Math.min(plotelAboveSolid[1][0], plotelAboveSolid[plotelAboveSolid.length - 1][0]) + offset;

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
        logger.log(Level.DEBUG, "creating CONVM elements finished");
        return convmForExport;
    }

}

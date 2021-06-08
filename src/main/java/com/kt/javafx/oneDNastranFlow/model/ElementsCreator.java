package com.kt.javafx.oneDNastranFlow.model;

import java.util.ArrayList;
import java.util.List;

public class ElementsCreator {

   public static List<String> createChbdyp(int offset, int[][] plotelAboveSolid, int propertyId, String surfaceType) {

        List<String> chbdypForExport = new ArrayList<>();
        int startNumber = Math.min(plotelAboveSolid[1][0], plotelAboveSolid[plotelAboveSolid.length - 1][0]);

        for (int i = 0; i < plotelAboveSolid.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CHBDYP")
                    .append(",")
                    .append(startNumber + offset + i)
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

        return chbdypForExport;
    }

    public static List<String> createConvm(int[][] plotelAboveSolid, int offset, int[][] plotelInSolid, int propertyId, int ctrlMassId) {
        List<String> convmForExport = new ArrayList<>();
        int startNumber = Math.min(plotelAboveSolid[1][0], plotelAboveSolid[plotelAboveSolid.length - 1][0]);

        for (int i = 0; i < plotelInSolid.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CONVM")
                    .append(",")
                    .append(startNumber + offset + i)
                    .append(",")
                    .append(propertyId + 1)
                    .append(",,")
                    .append(ctrlMassId)
                    .append(",")
                    .append(plotelInSolid[i][1])
                    .append(",")
                    .append(plotelInSolid[i][2]);
            convmForExport.add(stringBuilder.toString());
        }
        return convmForExport;
    }

}

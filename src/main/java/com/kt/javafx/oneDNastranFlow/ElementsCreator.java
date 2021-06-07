package com.kt.javafx.oneDNastranFlow;

import java.util.ArrayList;
import java.util.List;

class ElementsCreator {

    static List<String> createChbdyp(int offset, int[][] plotelAboveSolid, int propertyId, String surfaceType) {

        List<String> chbdypForExport = new ArrayList<>();

        for (int i = 0; i < plotelAboveSolid.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CHBDYP")
                    .append(",")
                    .append(plotelAboveSolid[i][0] + offset)
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

    static List<String> createConvm(int[][] plotelAboveSolid, int offset, int[][] plotelInSolid, int propertyId, int ctrlMassId) {
        List<String> convmForExport = new ArrayList<>();
        for (int i = 0; i < plotelInSolid.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CONVM")
                    .append(",")
                    .append(plotelAboveSolid[i][0] + offset)
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
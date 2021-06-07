package com.kt.javafx.oneDNastranFlow;

import java.util.Comparator;
import java.util.List;

class PlotelManipulator {

    int[][] plotelInSolid;
    int[][] plotelAboveSolid;
    private List<String> plotelInSolidList;
    private List<String> plotelAboveSolidList;

    void creteArrays(List<String> plotelListForProcessing){

        plotelInSolidList = plotelListForProcessing
                .subList(0,plotelListForProcessing.size()/2);
        plotelAboveSolidList = plotelListForProcessing
                .subList(plotelListForProcessing.size()/2,plotelListForProcessing.size());

        plotelInSolid = creteArray(plotelInSolidList, Sequence.DECREASING);
        plotelAboveSolid = creteArray(plotelAboveSolidList, Sequence.INCREASING);
    }

    private int [][] creteArray(List<String> inputPlotelList, Enum sequence){
        if(sequence.equals(Sequence.DECREASING)){
            inputPlotelList.sort(Comparator.reverseOrder());
        }
        int[][] plotelInSolid = new int[inputPlotelList.size()][3];
        for (int i = 0; i < inputPlotelList.size(); i++) {
            String s = inputPlotelList.get(i);
            String[] arrayForProcessing = s.split(",");
            plotelInSolid[i][0] = Integer.parseInt(arrayForProcessing[0]);
            plotelInSolid[i][1] = Integer.parseInt(arrayForProcessing[1]);
            plotelInSolid[i][2] = Integer.parseInt(arrayForProcessing[2]);
        }
        return plotelInSolid;
    }

}

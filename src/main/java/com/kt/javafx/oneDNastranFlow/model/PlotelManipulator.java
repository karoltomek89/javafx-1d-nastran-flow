package com.kt.javafx.oneDNastranFlow.model;

import java.util.Comparator;
import java.util.List;

 public class PlotelManipulator {

    int[][] plotelInSolid;
    int[][] plotelAboveSolid;
    private List<String> plotelInSolidList;
    private List<String> plotelAboveSolidList;

    public void creteArrays(List<String> plotelListForProcessing, Boolean differentOrder){

        plotelListForProcessing.sort(Comparator.naturalOrder());

        plotelInSolidList = plotelListForProcessing
                .subList(0,plotelListForProcessing.size()/2);
        plotelAboveSolidList = plotelListForProcessing
                .subList(plotelListForProcessing.size()/2,plotelListForProcessing.size());

        plotelInSolid = creteArray(plotelInSolidList, Boolean.FALSE);
        plotelAboveSolid = creteArray(plotelAboveSolidList, differentOrder);
    }

    private int [][] creteArray(List<String> inputPlotelList, Boolean differentOrder){

        if(differentOrder){
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

     public int[][] getPlotelInSolid() {
         return plotelInSolid;
     }

     public int[][] getPlotelAboveSolid() {
         return plotelAboveSolid;
     }
 }

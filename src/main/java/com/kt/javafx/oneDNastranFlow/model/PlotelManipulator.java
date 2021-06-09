package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

 public class PlotelManipulator {

    int[][] plotelInSolid;
    int[][] plotelAboveSolid;
    private List<String> plotelInSolidList;
    private List<String> plotelAboveSolidList;

    public void creteArrays(List<String> plotelListForProcessing, Boolean differentOrder, Logger logger){
        logger.log(Level.DEBUG, "reading elements started");

        plotelListForProcessing.sort(Comparator.naturalOrder());

        plotelInSolidList = plotelListForProcessing
                .subList(0,plotelListForProcessing.size()/2);
        plotelAboveSolidList = plotelListForProcessing
                .subList(plotelListForProcessing.size()/2,plotelListForProcessing.size());

        plotelInSolid = creteArray(plotelInSolidList, Boolean.FALSE);
        plotelAboveSolid = creteArray(plotelAboveSolidList, differentOrder);

        logger.log(Level.DEBUG, "reading elements finished");
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

        checkConsistency(plotelInSolid);

        return plotelInSolid;
    }

    boolean checkConsistency(int [][] arrayToTest){
        boolean isConsistent = true;
        for (int i = 0; i < arrayToTest.length -1 ; i++) {
            if (((arrayToTest[i][1] != arrayToTest[i +1][2]) & (arrayToTest[i][2] != arrayToTest[i +1][1]))){
                isConsistent = false;
                break;
            }
        }
        return isConsistent;
    }

     public int[][] getPlotelInSolid() {
         return plotelInSolid;
     }

     public int[][] getPlotelAboveSolid() {
         return plotelAboveSolid;
     }
 }

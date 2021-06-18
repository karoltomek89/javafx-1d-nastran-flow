package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PlotelManipulator {

    private int[][] plotelInSolid;
    private int[][] plotelAboveSolid;
    private List<String> plotelInSolidList;
    private List<String> plotelAboveSolidList;
    MySingleLogger mySingleLogger;
    Logger logger;

    public PlotelManipulator() {
        this.mySingleLogger = MySingleLogger.getInstance();
        this.logger = mySingleLogger.getLogger();
    }

    public void creteArrays(List<String> plotelListForProcessing, Boolean differentOrder) {

        try {
            Objects.requireNonNull(plotelListForProcessing);

            logger.info("Sorting PLOTEL elements");
            plotelListForProcessing.sort(Comparator.naturalOrder());

            plotelInSolidList = plotelListForProcessing
                    .subList(0, plotelListForProcessing.size() / 2);
            plotelAboveSolidList = plotelListForProcessing
                    .subList(plotelListForProcessing.size() / 2, plotelListForProcessing.size());

            plotelInSolid = creteArray(plotelInSolidList, Boolean.FALSE);
            plotelAboveSolid = creteArray(plotelAboveSolidList, differentOrder);

        } catch (NullPointerException e) {
            logger.error("No PLOTEL elements exist!");
        }
    }

    private int[][] creteArray(List<String> inputPlotelList, Boolean differentOrder) {

        try {
            Objects.requireNonNull(inputPlotelList);

            if (differentOrder) {
                inputPlotelList.sort(Comparator.reverseOrder());
                logger.info("Different PLOTEL groups sorting.");
            }

            plotelInSolid = new int[inputPlotelList.size()][3];

            for (int i = 0; i < inputPlotelList.size(); i++) {
                String s = inputPlotelList.get(i);
                String[] arrayForProcessing = s.split(",");
                plotelInSolid[i][0] = Integer.parseInt(arrayForProcessing[0]);
                plotelInSolid[i][1] = Integer.parseInt(arrayForProcessing[1]);
                plotelInSolid[i][2] = Integer.parseInt(arrayForProcessing[2]);
            }

            checkConsistency(plotelInSolid);

        } catch (NullPointerException e) {
            logger.error("No PLOTEL elements exist!");
        }

        return plotelInSolid;
    }

    boolean checkConsistency(int[][] arrayToTest) {
        logger.info("Checking consistency...");

        boolean isConsistent = false;
        try {
            Objects.requireNonNull(arrayToTest);

            isConsistent = true;

            for (int i = 0; i < arrayToTest.length - 1; i++) {
                if (((arrayToTest[i][1] != arrayToTest[i + 1][2]) & (arrayToTest[i][2] != arrayToTest[i + 1][1]))) {
                    isConsistent = false;
                    break;
                }
            }
            if (isConsistent) {
                logger.info("Consistency OK");
            } else {
                logger.error("Consistency not OK - check elements definition!");
            }
        } catch (NullPointerException e) {
            logger.error("No PLOTEL elements exist!");
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

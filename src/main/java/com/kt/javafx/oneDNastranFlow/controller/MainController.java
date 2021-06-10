package com.kt.javafx.oneDNastranFlow.controller;

import com.kt.javafx.oneDNastranFlow.model.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.util.List;


public class MainController {

    BDFReader bdfReader;
    PlotelManipulator plotelManipulator;
    PropertiesCreator propertiesCreator;
    MyLogger myLogger;
    Logger logger;

    public MainController(BDFReader bdfReader, PlotelManipulator plotelManipulator,
                          PropertiesCreator propertiesCreator, MyLogger myLogger) {
        this.bdfReader = bdfReader;
        this.plotelManipulator = plotelManipulator;
        this.propertiesCreator = propertiesCreator;
        this.myLogger = myLogger;
    }

    public void processFiles(String offset, String ctrlMassId, String phbdyPropertyId, String diameter1,
                             String diameter2, String pconvPropertyId, String matId, String formulaType,
                             String massFlowConvection, String pathToBDF, String medium, Boolean differentOrder,
                             String surfaceType)
            throws IOException {

        logger = myLogger.startLogging(pathToBDF);

        logger.log(Level.DEBUG, "start processing file " + pathToBDF);

        plotelManipulator.creteArrays(bdfReader.readBDF(pathToBDF), differentOrder, logger);
        propertiesCreator.createPHBDY(phbdyPropertyId, diameter1, diameter2, logger);
        propertiesCreator.createCONVM(pconvPropertyId, matId, formulaType, massFlowConvection, logger);
        propertiesCreator.createMAT4(matId, medium, logger);

        List<String> generatedChbdyp = ElementsCreator
                .createChbdyp(Integer.parseInt(offset),
                        plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(phbdyPropertyId),
                        surfaceType, logger);

        List<String> generatedConvm = ElementsCreator
                .createConvm(plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(offset),
                        plotelManipulator.getPlotelInSolid(),
                        Integer.parseInt(pconvPropertyId),
                        Integer.parseInt(ctrlMassId), logger);

        Saver.saveResult(generatedChbdyp, generatedConvm, propertiesCreator.getProperties(), pathToBDF, logger);

    }

    public void clear() {
        logger.log(Level.DEBUG, "clear input data");
        propertiesCreator.clear();
        bdfReader.clear();
        logger.log(Level.DEBUG, "end of log");
        myLogger.stopLogging();
    }
}

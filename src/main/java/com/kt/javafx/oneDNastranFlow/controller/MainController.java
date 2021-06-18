package com.kt.javafx.oneDNastranFlow.controller;

import com.kt.javafx.oneDNastranFlow.model.*;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class MainController {

    BDFReader bdfReader;
    PlotelManipulator plotelManipulator;
    PropertiesCreator propertiesCreator;
    ElementsCreator elementsCreator;
    MySingleLogger mySingleLogger;
    Logger logger;

    public MainController(BDFReader bdfReader, PlotelManipulator plotelManipulator,
                          PropertiesCreator propertiesCreator, ElementsCreator elementsCreator) {
        this.bdfReader = bdfReader;
        this.plotelManipulator = plotelManipulator;
        this.propertiesCreator = propertiesCreator;
        this.elementsCreator = elementsCreator;
        this.mySingleLogger = MySingleLogger.getInstance();
    }

    public void processFiles(String offset, String ctrlMassId, String phbdyPropertyId, String diameter1,
                             String diameter2, String pconvPropertyId, String matId, String formulaType,
                             String massFlowConvection, String pathToBDF, String medium, Boolean differentOrder,
                             String surfaceType) {

        mySingleLogger.startLogging(pathToBDF);
        logger = mySingleLogger.getLogger();
        logger.info("Processing file started...");

        plotelManipulator.creteArrays(bdfReader.readBDF(pathToBDF), differentOrder);
        propertiesCreator.createPHBDY(phbdyPropertyId, diameter1, diameter2);
        propertiesCreator.createCONVM(pconvPropertyId, matId, formulaType, massFlowConvection);
        propertiesCreator.createMAT4(matId, medium);

        List<String> generatedChbdyp = elementsCreator
                .createChbdyp(Integer.parseInt(offset),
                        plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(phbdyPropertyId),
                        surfaceType);

        List<String> generatedConvm = elementsCreator
                .createConvm(plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(offset),
                        plotelManipulator.getPlotelInSolid(),
                        Integer.parseInt(pconvPropertyId),
                        Integer.parseInt(ctrlMassId));

        Saver.saveResult(generatedChbdyp, generatedConvm, propertiesCreator.getProperties(), pathToBDF);

        logger.info("Processing file finished.");
    }

    public void clear() {
        logger.info("Clear input data.");
        propertiesCreator.clear();
        bdfReader.clear();
        elementsCreator.clear();
        logger.info( "End of log.");
        mySingleLogger.stopLogging();
    }
}

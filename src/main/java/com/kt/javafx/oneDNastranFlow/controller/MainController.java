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
    MySingleLogger mySingleLogger = MySingleLogger.getInstance();
    Logger logger;

    public MainController(BDFReader bdfReader, PlotelManipulator plotelManipulator,
                          PropertiesCreator propertiesCreator) {
        this.bdfReader = bdfReader;
        this.plotelManipulator = plotelManipulator;
        this.propertiesCreator = propertiesCreator;

    }

    public void processFiles(String offset, String ctrlMassId, String phbdyPropertyId, String diameter1,
                             String diameter2, String pconvPropertyId, String matId, String formulaType,
                             String massFlowConvection, String pathToBDF, String medium, Boolean differentOrder,
                             String surfaceType)
            throws IOException {

        mySingleLogger.startLogging(pathToBDF);
        logger = mySingleLogger.getLogger();


        plotelManipulator.creteArrays(bdfReader.readBDF(pathToBDF), differentOrder);
        propertiesCreator.createPHBDY(phbdyPropertyId, diameter1, diameter2);
        propertiesCreator.createCONVM(pconvPropertyId, matId, formulaType, massFlowConvection);
        propertiesCreator.createMAT4(matId, medium);

        List<String> generatedChbdyp = ElementsCreator
                .createChbdyp(Integer.parseInt(offset),
                        plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(phbdyPropertyId),
                        surfaceType);

        List<String> generatedConvm = ElementsCreator
                .createConvm(plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(offset),
                        plotelManipulator.getPlotelInSolid(),
                        Integer.parseInt(pconvPropertyId),
                        Integer.parseInt(ctrlMassId));

        Saver.saveResult(generatedChbdyp, generatedConvm, propertiesCreator.getProperties(), pathToBDF);

    }

    public void clear() {
        logger.log(Level.DEBUG, "clear input data");
        propertiesCreator.clear();
        bdfReader.clear();
        logger.log(Level.DEBUG, "end of log");
        mySingleLogger.stopLogging();
    }
}

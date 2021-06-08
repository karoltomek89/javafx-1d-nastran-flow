package com.kt.javafx.oneDNastranFlow.controller;

import com.kt.javafx.oneDNastranFlow.model.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainController {

    BDFReader bdfReader;
    PlotelManipulator plotelManipulator;
    PropertiesCreator propertiesCreator;

    public MainController(BDFReader bdfReader, PlotelManipulator plotelManipulator, PropertiesCreator propertiesCreator) {
        this.bdfReader = bdfReader;
        this.plotelManipulator = plotelManipulator;
        this.propertiesCreator = propertiesCreator;
    }

    public void processFiles(String offset, String ctrlMassId, String phbdyPropertyId, String diameter1, String diameter2,
                             String pconvPropertyId, String matId, String formulaType,
                             String massFlowConvection, String pathToBDF, String medium) throws IOException{

        plotelManipulator.creteArrays(bdfReader.readBDF(pathToBDF));

        propertiesCreator.createPHBDY(phbdyPropertyId, diameter1, diameter2);
        propertiesCreator.createCONVM(pconvPropertyId, matId, formulaType, massFlowConvection);
        propertiesCreator.createMAT4(matId, medium);

        List<String> test1 = ElementsCreator
                .createChbdyp(Integer.parseInt(offset),
                        plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(phbdyPropertyId),
                        "FTUBE");
        List<String> test2 = ElementsCreator
                .createConvm(plotelManipulator.getPlotelAboveSolid(),
                        Integer.parseInt(offset),
                        plotelManipulator.getPlotelInSolid(),
                        Integer.parseInt(pconvPropertyId),
                        Integer.parseInt(ctrlMassId));

        Saver.saveResult(test1, test2, propertiesCreator.getProperties(), pathToBDF);
    }

    public void clear(){
        propertiesCreator.clear();
        bdfReader.clear();
    }
}

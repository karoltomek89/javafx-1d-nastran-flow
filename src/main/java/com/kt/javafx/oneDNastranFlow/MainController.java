package com.kt.javafx.oneDNastranFlow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class MainController {

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
                             String massFlowConvection, String pathToBDF) throws IOException{

        plotelManipulator.creteArrays(bdfReader.readBDF(pathToBDF));

        propertiesCreator.createPHBDY(phbdyPropertyId, diameter1, diameter2);
        propertiesCreator.createCONVM(pconvPropertyId, matId, formulaType, massFlowConvection);
        propertiesCreator.createMAT4(matId);

        List<String> test1 = ElementsCreator
                .createChbdyp(Integer.parseInt(offset),
                        plotelManipulator.plotelAboveSolid,
                        Integer.parseInt(phbdyPropertyId),
                        "FTUBE");
        List<String> test2 = ElementsCreator
                .createConvm(plotelManipulator.plotelAboveSolid,
                        Integer.parseInt(offset),
                        plotelManipulator.plotelInSolid,
                        Integer.parseInt(pconvPropertyId),
                        Integer.parseInt(ctrlMassId));

        BufferedWriter writer = Files.newBufferedWriter(Paths.get("result.txt"));

        for (String s : test1) {
            writer.write(s + "\n");
        }
        for (String s : test2) {
            writer.write(s + "\n");
        }
        for (String s : propertiesCreator.properties) {
            writer.write(s + "\n");
        }
        writer.close();


    }

    void clear(){
        propertiesCreator.clear();
        bdfReader.clear();
    }

}

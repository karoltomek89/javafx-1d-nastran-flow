package com.kt.javafx.oneDNastranFlow.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PropertiesCreator {

    private List<String> properties;
    private Medium medium;
    MySingleLogger mySingleLogger;
    Logger logger;

   public PropertiesCreator() {
        this.properties = new ArrayList<>();
        this.mySingleLogger = MySingleLogger.getInstance();
        this.logger = mySingleLogger.getLogger();
    }

    public void createPHBDY(String phbdyPropertyId, String diameter1, String diameter2) {

        try {
            Objects.requireNonNull(phbdyPropertyId);
            Objects.requireNonNull(diameter1);
            Objects.requireNonNull(diameter2);

            logger.info("Creating PHBDY properties...");

            properties.add("$PHBDY CHBDYP Geometric Element Definition");
            properties.add("$PHBDY, Property ID, , Diameter 1, Diameter 2");
            properties.add("PHBDY," + phbdyPropertyId + ",," + diameter1 + "," + diameter2);

        } catch (NullPointerException e) {
            logger.error("Empty input data!");
        }
        logger.info("PHBDY properties created.");
    }

    public void createCONVM(String pconvPropertyId, String matId, String formulaType, String massFlowConvection) {
        try {
            Objects.requireNonNull(pconvPropertyId);
            Objects.requireNonNull(matId);
            Objects.requireNonNull(formulaType);
            Objects.requireNonNull(massFlowConvection);

            logger.info("Creating CONVM properties...");

            properties.add("$PCONVM Forced Convection Property Definition");
            properties.add("$PCONVM, Convection property ID, Material Property ID, Convection formula type, Flag mass flow convection");
            properties.add("PCONVM," + pconvPropertyId + "," + matId + "," + formulaType + "," + massFlowConvection + ",0.023,0.8,0.4,0.3");

            logger.info("CONVM properties created.");

        } catch (NullPointerException e) {
            logger.error("Empty input data!");
        }
    }

    public void createMAT4(String matId, String typedMedium) {

        try {
            Objects.requireNonNull(matId);
            Objects.requireNonNull(typedMedium);

            logger.info("Creating MAT4 properties...");

            properties.add("$MAT4 Heat Transfer Material Properties, Isotropic");
            properties.add("$MAT4, Material ID, Conductivity, Heat capacity, Density, Free convection coefficient, Dynamic viscosity,");

            switch (typedMedium) {
                case "WATER_20C_T_MM_S":
                    medium = Medium.WATER_20C_T_MM_S;
                case "AIR_20C_T_MM_S":
                    medium = Medium.AIR_20C_T_MM_S;
            }

            properties.add("MAT4," + matId
                    + "," +
                    medium.getConductivity() +
                    "," +
                    medium.getHeatCapacity() +
                    "," +
                    medium.getDensity() +
                    ",," +
                    medium.getDynamicViscosity());

            logger.info("MAT4 properties created.");

        } catch (NullPointerException e) {
            logger.error("Empty input data!");
        }
    }

    public void clear() {
        properties = new ArrayList<>();
    }

    public List<String> getProperties() {
        return properties;
    }
}

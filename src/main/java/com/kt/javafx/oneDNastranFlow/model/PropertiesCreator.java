package com.kt.javafx.oneDNastranFlow.model;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    }

    public void createPHBDY(String phbdyPropertyId, String diameter1, String diameter2) {
        logger = mySingleLogger.getLogger();

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
        logger = mySingleLogger.getLogger();

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
        logger = mySingleLogger.getLogger();

        try {
            Objects.requireNonNull(matId);
            Objects.requireNonNull(typedMedium);

            logger.info("Creating MAT4 properties...");

            properties.add("$MAT4 Heat Transfer Material Properties, Isotropic");
            properties.add("$MAT4, Material ID, Conductivity, Heat capacity, Density, Free convection coefficient, Dynamic viscosity,");

            switch (typedMedium) {
                case "WATER_20C_T_MM_S":
                    medium = Medium.WATER_20C_T_MM_S;
                    extractAndAddParameters(matId, medium);
                    break;
                case "AIR_20C_T_MM_S":
                    medium = Medium.AIR_20C_T_MM_S;
                    extractAndAddParameters(matId, medium);
                    break;
                case "WATER_20C_KG_M_S":
                    medium = Medium.WATER_20C_KG_M_S;
                    extractAndAddParameters(matId, medium);
                    break;
                case "AIR_20C_KG_M_S":
                    medium = Medium.AIR_20C_KG_M_S;
                    extractAndAddParameters(matId, medium);
                    break;
                case "OTHER":
                    addOtherParameters(matId);
                    break;
            }

            logger.info("MAT4 properties created.");

        } catch (NullPointerException e) {
            logger.error("Empty input data!");
            AlertCreator.createError("Empty input data!", "Check the selected properties!");
        }
    }

    private void extractAndAddParameters(String matId, Medium medium) {
        properties.add("MAT4," + matId
                + "," +
                medium.getConductivity() +
                "," +
                medium.getHeatCapacity() +
                "," +
                medium.getDensity() +
                ",," +
                medium.getDynamicViscosity());
    }

    private void addOtherParameters(String matId) {

        Dialog dialog = new Dialog<>();
        dialog.setTitle("1D Nastran Flow");
        dialog.setHeaderText("Type medium information");

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField otherConductivity = new TextField();
        TextField otherHeatCapacity = new TextField();
        TextField otherDensity = new TextField();
        TextField otherDynamicViscosity = new TextField();

        grid.add(new Label("Conductivity:"), 0, 0);
        grid.add(otherConductivity, 1, 0);
        grid.add(new Label("Heat Capacity:"), 0, 1);
        grid.add(otherHeatCapacity, 1, 1);
        grid.add(new Label("Density:"), 0, 2);
        grid.add(otherDensity, 1, 2);
        grid.add(new Label("Dynamic Viscosity:"), 0, 3);
        grid.add(otherDynamicViscosity, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait();

        if (otherConductivity.getText().isEmpty() || otherHeatCapacity.getText().isEmpty() ||
                otherDensity.getText().isEmpty() || otherDynamicViscosity.getText().isEmpty()) {

            logger.warn("Probably incorect medium data.");
            AlertCreator.createError("Probably incorect medium data!", "Check the medium data.");
        }

        properties.add("MAT4," + matId
                + "," +
                otherConductivity.getText() +
                "," +
                otherHeatCapacity.getText() +
                "," +
                otherDensity.getText() +
                ",," +
                otherDynamicViscosity.getText());
    }

    public void clear() {
        properties = new ArrayList<>();
    }

    public List<String> getProperties() {
        return properties;
    }
}

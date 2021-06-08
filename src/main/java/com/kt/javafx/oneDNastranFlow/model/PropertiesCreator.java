package com.kt.javafx.oneDNastranFlow.model;

import java.util.ArrayList;
import java.util.List;

public class PropertiesCreator {

    List<String> properties = new ArrayList<>();
    Medium medium;

    public void createPHBDY(String phbdyPropertyId, String diameter1, String diameter2) {
        properties.add("$PHBDY CHBDYP Geometric Element Definition");
        properties.add("$PHBDY, Property ID, , Diameter 1, Diameter 2");
        properties.add("PHBDY," + phbdyPropertyId + ",," + diameter1 + "," + diameter2);
    }

    public void createCONVM(String pconvPropertyId, String matId, String formulaType, String massFlowConvection) {

        properties.add("$PCONVM Forced Convection Property Definition");
        properties.add("$PCONVM, Convection property ID, Material Property ID, Convection formula type, Flag mass flow convection");
        properties.add("PCONVM," + pconvPropertyId + "," + matId + "," + formulaType + "," + massFlowConvection + ",0.023,0.8,0.4,0.3");

    }

    public void createMAT4(String matId, String typedMedium) {

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
    }

    public void clear() {
        properties = new ArrayList<>();
    }

    public List<String> getProperties() {
        return properties;
    }
}

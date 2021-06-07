package com.kt.javafx.oneDNastranFlow;

import java.util.ArrayList;
import java.util.List;

class PropertiesCreator {

    List<String> properties = new ArrayList<>();

    void createPHBDY(String phbdyPropertyId, String diameter1, String diameter2) {
        properties.add("$PHBDY CHBDYP Geometric Element Definition");
        properties.add("$PHBDY, Property ID, , Diameter 1, Diameter 2");
        properties.add("PHBDY," + phbdyPropertyId + ",," + diameter1 + "," + diameter2);
    }

    void createCONVM(String pconvPropertyId, String matId, String formulaType, String massFlowConvection) {

        properties.add("$PCONVM Forced Convection Property Definition");
        properties.add("$PCONVM, Convection property ID, Material Property ID, Convection formula type, Flag mass flow convection");
        properties.add("PCONVM," + pconvPropertyId + "," + matId + "," + formulaType + "," + massFlowConvection + ",0.023,0.8,0.4,0.3");

    }

    void createMAT4(String matId) {

        properties.add("$MAT4 Heat Transfer Material Properties, Isotropic");
        properties.add("$MAT4, Material ID, Conductivity, Heat capacity, Density, Free convection coefficient, Dynamic viscosity,");
        properties.add("MAT4,"+matId+",0.65,4.2e9,1e-9,,1.0E-9");
    }

    void clear(){
        properties = new ArrayList<>();
    }
}
package com.kt.javafx.oneDNastranFlow;

enum Medium {
    AIR_20C_T_MM_S("0.65","4.2e9","1e-9","1.0E-9"),
    WATER_20C_T_MM_S("0.65","4.2e9","1e-9","1.0E-9");

    private String conductivity;
    private String heatCapacity;
    private String density;
    private String dynamicViscosity;

    Medium(String conductivity, String heatCapacity, String density, String dynamicViscosity) {
        this.conductivity = conductivity;
        this.heatCapacity = heatCapacity;
        this.density = density;
        this.dynamicViscosity = dynamicViscosity;
    }

    String getConductivity() {
        return conductivity;
    }

    String getHeatCapacity() {
        return heatCapacity;
    }

    String getDensity() {
        return density;
    }

    String getDynamicViscosity() {
        return dynamicViscosity;
    }


}

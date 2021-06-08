package com.kt.javafx.oneDNastranFlow.model;

public enum Medium {
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

    public String getConductivity() {
        return conductivity;
    }

    public String getHeatCapacity() {
        return heatCapacity;
    }

    public String getDensity() {
        return density;
    }

    public String getDynamicViscosity() {
        return dynamicViscosity;
    }


}

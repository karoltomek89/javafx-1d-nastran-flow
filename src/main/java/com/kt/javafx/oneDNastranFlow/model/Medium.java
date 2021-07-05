package com.kt.javafx.oneDNastranFlow.model;

public enum Medium {
    AIR_20C_T_MM_S("0.025","1e9","1e-12","1.82e-11"),
    WATER_20C_T_MM_S("0.6","4.2e9","1e-9","1e-9"),
    AIR_20C_KG_M_S("0.025","1000","1.2","1.82E-5"),
    WATER_20C_KG_M_S("0.6","4200","1000","0.001");

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

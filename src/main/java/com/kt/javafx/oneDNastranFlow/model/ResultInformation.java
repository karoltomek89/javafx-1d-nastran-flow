package com.kt.javafx.oneDNastranFlow.model;

public class ResultInformation {

    static int numberPlotelElements = 0;
    static boolean consistency = true;
    static int numberFirstChbdyp = 0;
    static int numberChbdypElements = 0;
    static int numberFirstConvm = 0;
    static int numberConvmElements = 0;

    public static void clear(){
        numberPlotelElements = 0;
        consistency = true;
        numberFirstChbdyp = 0;
        numberChbdypElements = 0;
        numberFirstConvm = 0;
        numberConvmElements = 0;
    }

    public static int getNumberPlotelElements() {
        return numberPlotelElements;
    }

    public  static boolean isConsistency() {
        return consistency;
    }

    public  static int getNumberFirstChbdyp() {
        return numberFirstChbdyp;
    }

    public static int getNumberChbdypElements() {
        return numberChbdypElements;
    }

    public static int getNumberFirstConvm() {
        return numberFirstConvm;
    }

    public static int getNumberConvmElements() {
        return numberConvmElements;
    }
}

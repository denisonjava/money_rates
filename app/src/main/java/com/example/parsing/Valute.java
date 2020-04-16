package com.example.parsing;

import java.io.UnsupportedEncodingException;

public class Valute {

    public Valute() throws UnsupportedEncodingException {    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {  Name = name; }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    private String numCode;
    private String charCode;
    private String nominal;
    private String Name;
    private String value;

    public String toString(){
        //return  "valute: " + numCode + " - " + charCode + " - " + nominal + " - " + Name + " - " + value;
        return  charCode + " " + nominal + " " + Name + " " + value;
}
}

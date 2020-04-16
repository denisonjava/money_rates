package com.example.parsing;


public class ParsingValute {


    public ParsingValute(String charCode, String nominal, String name, String value) {
        this.charCode = charCode;
        this.nominal = nominal;
        this.Name = name;
        this.value = value;
    }

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
        }




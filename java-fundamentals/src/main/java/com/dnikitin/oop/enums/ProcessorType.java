package com.dnikitin.oop.enums;

//enum jak zwykły klas, nasladujące ktorego mogą byc tylko te obiekty
public enum ProcessorType {
    INTEL("name1"){
        @Override
        public String getDescription() {
            return "intel";
        }
    },
    AMD("name2"){
        @Override
        public String getDescription() {
            return "amd";
        }
    };

    private String name;

    ProcessorType(String name){
        this.name = name;
    }

    public abstract String getDescription(); //mozna tworzyc abstraktne metody

    public String getName(){
        return name;
    }
}

package oop.enums;

import java.util.Arrays;

public class EnumRunner {
    public static void main(String[] args) {
        ProcessorType type = ProcessorType.INTEL; //mamy do wyboru tylko dwie opcji, ktore są w enum
        System.out.println(type);
        System.out.println(type.name());
        System.out.println(ProcessorType.valueOf("AMD"));
        System.out.println(ProcessorType.AMD.ordinal()); //dostac index tej konkretnej wartosci enuma

        System.out.println(Arrays.toString(ProcessorType.values())); //dostac wszystkie wartosci enuma

        System.out.println(ProcessorType.INTEL.getName());
    }
}

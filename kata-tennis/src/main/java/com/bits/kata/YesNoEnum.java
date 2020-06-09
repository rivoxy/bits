package com.bits.kata;

/**
 *
 * @author Rivo
 */
public enum YesNoEnum {
    Y("Oui"), N("Non");

    private String name = "";

    YesNoEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

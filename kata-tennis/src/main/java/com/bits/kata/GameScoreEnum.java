package com.bits.kata;

/**
 *
 * @author Rivo
 */
public enum GameScoreEnum {
    P0("0"), P15("15"), P30("30"), P40("40"), PADV("ADV");

    private String name = "";

    GameScoreEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

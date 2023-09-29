package main.tricks;

import java.util.HashMap;
import java.util.Map;

public enum EnumTest2 {
    ADV1("1234567"),
    ADV1_BCD("11"),
    ADV2("1234568"),
    ADV2_BCD("21"),
    ADV3("1234569"),
    ADV3_BCD("24");

    final String value;

    private EnumTest2(String value) { this.value = value; }

    public String getValue() { return this.value; }
}

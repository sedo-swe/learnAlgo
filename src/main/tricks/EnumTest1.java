package main.tricks;

import java.util.HashMap;
import java.util.Map;

public enum EnumTest1 {
    ADV1("1234567", "11"),
    ADV2("1234568", "21"),
    ADV3("1234569", "24");

    private static final Map<String, EnumTest1> BY_LABEL = new HashMap<>();
    private static final Map<String, EnumTest1> BY_DATE = new HashMap<>();

    static {
        for (EnumTest1 e : values()) {
            BY_LABEL.put(e.id, e);
        }
    }

    public final String id;
    public final String bcdDate;

    private EnumTest1(String id, String bcdDate) {
        this.id = id;
        this.bcdDate = bcdDate;
    }

    public static EnumTest1 valueOfId(String id) {
        return BY_LABEL.get(id);
    }

    public static EnumTest1 valueOfBcdDate(String bcdDate) {
        return BY_DATE.get(bcdDate);
    }
}

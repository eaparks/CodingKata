package com.parks.kata.berlinclock;

import java.util.HashMap;
import java.util.Map;

public class FiveMinuteRow {

    public static Map<Integer, String> lights = new HashMap<>();
    static {
        lights.put(0, "OOOOOOOOOOO");
        lights.put(1, "YOOOOOOOOOO");
        lights.put(2, "YYOOOOOOOOO");
        lights.put(3, "YYROOOOOOOO");
        lights.put(4, "YYRYOOOOOOO");
        lights.put(5, "YYRYYOOOOOO");
        lights.put(6, "YYRYYROOOOO");
        lights.put(7, "YYRYYRYOOOO");
        lights.put(8, "YYRYYRYYOOO");
        lights.put(9, "YYRYYRYYROO");
        lights.put(10, "YYRYYRYYRYO");
        lights.put(11, "YYRYYRYYRYY");
    }
}

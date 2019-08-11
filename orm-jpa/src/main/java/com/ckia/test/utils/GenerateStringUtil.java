package com.ckia.test.utils;

public class GenerateStringUtil {

    public static String generateName() {
        int randomInt = (Double.valueOf(Math.random() * 10000)).intValue();
        return "s"+randomInt+(Double.valueOf(Math.random() * 100)).intValue();
    }
    public static Integer generateId(Integer level) {
        return (Double.valueOf(Math.random() * level)).intValue();
    }
}

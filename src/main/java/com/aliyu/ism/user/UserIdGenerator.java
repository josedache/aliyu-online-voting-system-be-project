package com.aliyu.ism.user;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UserIdGenerator {
    static final Random rand = new Random();

    public static int getRandomNumber() {
        return rand.nextInt(9000000) + 1000000;
    }

    public static String getVoterId() {
        Calendar calendar = Calendar.getInstance();
        return String.format("VTR-%d-%d-%d",
                getRandomNumber(),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
    }

    public static String getCandidate() {
        Calendar calendar = Calendar.getInstance();
        return String.format("CDT-%d-%d-%d",
                getRandomNumber(),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
    }

    public static String getAdminId() {
        Calendar calendar = Calendar.getInstance();

        return String.format("ADM-%d-%d-%d",
                getRandomNumber(),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
    }

}

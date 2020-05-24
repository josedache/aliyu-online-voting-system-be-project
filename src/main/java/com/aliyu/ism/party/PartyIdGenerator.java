package com.aliyu.ism.party;

import java.util.Calendar;
import java.util.Random;

public class PartyIdGenerator {

    static final Random rand = new Random();

    public static int getRandomNumber() {
        return rand.nextInt(9000000) + 1000000;
    }

    public static String getPartyId() {
        Calendar calendar = Calendar.getInstance();
        return String.format("PTY-%d-%d-%d",
                getRandomNumber(),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
    }
}

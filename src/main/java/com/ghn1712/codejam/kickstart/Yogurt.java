package com.ghn1712.codejam.kickstart;

import java.util.Arrays;

public class Yogurt {

    public static int getMaximumNumberCups(int maximumNumberCupsPerDay, int[] bestBeforeDate) {
        Arrays.sort(bestBeforeDate);
        int numberConsumedTotal = 0;
        int today = 0;
        int index = 0;
        while (index < bestBeforeDate.length) {
            int numbersConsumedToday = 0;
            while (numbersConsumedToday < maximumNumberCupsPerDay && index < bestBeforeDate.length) {
                if (bestBeforeDate[index] > today) {
                    numbersConsumedToday++;
                }
                index++;
            }
            numberConsumedTotal += numbersConsumedToday;
            today++;
        }
        return numberConsumedTotal;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumNumberCups(2, new int[] { 1, 1, 1, 7, 7, 7 }));
    }
}

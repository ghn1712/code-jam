package com.ghn1712.codejam.kickstart;

import java.util.Arrays;

public class MilkTea {

    public static int getSmallestNumberOfComplaints(String[] preferences, String[] forbiddenCombinations,
            int bytesNumber) {
        int minComplaints = 0;
        boolean firstComparation = true;
        String[] combinations = getPossibleCombinations(bytesNumber, forbiddenCombinations);
        for (String combinationText : combinations) {
            if (combinationText != null) {
                int complaintsPerCombination = 0;
                for (String preference : preferences) {
                    complaintsPerCombination += computeComplaintsPerPreference(combinationText, preference);
                }
                if (firstComparation) {
                    minComplaints = complaintsPerCombination;
                    firstComparation = false;
                }
                else {
                    if (minComplaints > complaintsPerCombination) {
                        minComplaints = complaintsPerCombination;
                    }
                }
            }
        }
        return minComplaints;
    }

    private static int computeComplaintsPerPreference(String combinationText, String preference) {
        int complaints = 0;
        byte[] combination = getBytesFromText(combinationText);
        byte[] preferenceOptions = getBytesFromText(preference);
        for (int index = 0; index < preferenceOptions.length; index++) {
            complaints += preferenceOptions[index] ^ combination[index];
        }
        return complaints;
    }

    private static byte[] getBytesFromText(String text) {
        char[] characters = text.toCharArray();
        byte[] bytes = new byte[characters.length];
        for (int i = 0; i < characters.length; i++) {
            bytes[i] = (byte) Character.getNumericValue(characters[i]);
        }
        return bytes;
    }

    private static String[] getPossibleCombinations(int bytesNumber, String[] forbiddenCombinations) {
        Arrays.sort(forbiddenCombinations);
        int combinationsNumber = (int) Math.pow(2, bytesNumber);
        String[] combinations = new String[combinationsNumber];
        for (int i = 0; i < combinationsNumber; i++) {
            String binaryNumber = Integer.toBinaryString(i);
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < bytesNumber - binaryNumber.length(); j++) {
                stringBuilder.append('0');
            }
            String combination = stringBuilder.append(binaryNumber).toString();
            if (Arrays.binarySearch(forbiddenCombinations, combination) < 0) {
                combinations[i] = combination;
            }
        }
        return combinations;
    }

    public static void main(String[] args) {
        System.out.println("Case #1: "
                + getSmallestNumberOfComplaints(new String[] { "1100", "1010", "0000" }, new String[] { "1000" }, 4));
        System.out.println("Case #2: " + getSmallestNumberOfComplaints(new String[] { "1111", "1111" },
                new String[] { "1111", "0111", "1011", "1101" }, 4));
    }
}

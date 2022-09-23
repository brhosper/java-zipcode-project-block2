package com.kenzie.app.format;

import java.util.ArrayList;
import java.util.HashMap;

public class AddressFormatUtil {
    //declare properties
    private static HashMap<String, String> codeTable = new HashMap<>();
    private static ArrayList<ArrayList<String>> listItems;


    //static initializer - runs automatically
    static {
        codeTable.put("ST", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("RD", "ROAD");
        codeTable.put("Ave.", "AVENUE");
        codeTable.put("Ave", "AVENUE");

    }

    public static void initCodeTable() {
        //enter values into HashMap
        codeTable.put("ST", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("RD", "ROAD");
        codeTable.put("Ave.", "AVENUE");
        codeTable.put("Ave", "AVENUE");



    }

    //123 Main St. - input
    //123 Main STREET - output
    public static String replaceAbbreviation(String input){
        String result = input;

        for (String currentKey : codeTable.keySet()) {
            //search and replace
            if (input.contains(currentKey)) {
                String currentValue = codeTable.get(currentKey);
                result = input.replace(currentKey, (currentValue));

            }
        }
        return result;
    }

    public static void main(String[] args) {
        String testAddr = "123 Main St.";
        String testAddr2 = "123 Main RD";
        String testAddr3 = "123 Main Ave";
        initCodeTable();
        System.out.println(replaceAbbreviation(testAddr));
        System.out.println(replaceAbbreviation(testAddr2));
        System.out.println(replaceAbbreviation(testAddr3));

        //Test Avenue

        //Test Road
    }


}

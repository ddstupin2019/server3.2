package com.example.prodanog.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class ParserString {

    public static ArrayList<Integer> parserString(String in){
        ArrayList<Integer> rez= new ArrayList<>();
        if(in!=null){
            Scanner scanner = new Scanner(in);
            while (scanner.hasNextInt()) {
                rez.add(scanner.nextInt());
            }
        }
        return rez;
    }
    public static String parserArray(ArrayList<Integer> inp){
        String rez = "";
        for (int i = 0; i < inp.size(); i++) {
            rez+=String.valueOf(inp.get(i))+' ';
        }
        return rez;
    }
}

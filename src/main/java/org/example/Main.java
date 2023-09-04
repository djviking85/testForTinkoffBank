package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer money = 77;
        Map<Integer, Integer> revol = new HashMap<>();
        revol.put(1, 22);
        revol.put(2, 11);
        revol.put(3, 1);
        revol.put(4, 26);
        revol.put(5, 76);
        Map.Entry<Integer, Integer> maxPrice = null;
        for (Map.Entry<Integer, Integer> entry : revol.entrySet()) {
            if (maxPrice == null || entry.getValue() > maxPrice.getValue()) {
                maxPrice = entry;
            }
        }

        if (maxPrice.getValue() > money) {
            System.out.println("Такого револьвера нет выводим: 0");
        } else {
            if (money >= maxPrice.getValue()) {
                System.out.println("Джо может купить револьвер по цене " + maxPrice.getValue());
            }
        }
    }
}





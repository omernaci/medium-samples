package com.omernaci;

import java.util.*;

public class InstanceOfExample {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        if (strings instanceof ArrayList a) {
            System.out.println("Collection's hashcode is: " + a.hashCode());
        } else {
            System.out.println("Not a Collection of specified type");
        }
    }

}

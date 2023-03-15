package com.omernaci;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InstanceOfSample {

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();

        if (stringList instanceof LinkedList) {
            System.out.println("old style");
        }

        if (stringList instanceof ArrayList a) {
            System.out.println(a.hashCode());
        }


    }

}

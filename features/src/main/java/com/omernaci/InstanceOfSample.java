package com.omernaci;

public class InstanceOfSample {

    public static void main(String[] args) {
        Object obj1 = "omernaci";
        Object obj2 = 42;
        Object obj3 = new Object();

        processObject(obj1); // Output: Input is a string: Hello
        processObject(obj2); // Output: Input is an integer: 42
        processObject(obj3); // Output: Input is of unknown type
    }

    public static void processObject(Object obj) {
        if (obj instanceof String str) {
            System.out.println("Input is a string: " + str);
        } else if (obj instanceof Integer i) {
            System.out.println("Input is an integer: " + i);
        } else {
            System.out.println("Input is of unknown type");
        }
    }

}

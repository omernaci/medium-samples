package com.omernaci;

public class NewString {

    public static void main(String[] args) {
        // isBlank() method
        System.out.println(" ".isBlank()); // true

        String string = "  omernaci  ";
        System.out.println(string.isBlank()); // false

        String string1 = "";
        System.out.println(string1.isBlank()); // true

        // repeat(int count) method
        String repeatedString = "omer".repeat(2);
        System.out.println(repeatedString); // prints omeromer

        // strip(), stripLeading() and stripTrailing() methods
        String str = " JD ";
        System.out.print("Start");
        System.out.print(str.strip());
        System.out.println("End");

        System.out.print("Start");
        System.out.print(str.stripLeading());
        System.out.println("End");

        System.out.print("Start");
        System.out.print(str.stripTrailing());
        System.out.println("End");

    }

}

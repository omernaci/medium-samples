package com.omernaci;

public class TextBlocks {

    public static void main(String[] args) {

        // Using a literal string
        String literalString = "omernaci";

        // Using a text block
        String textBlockString = """
                omernaci""";

        System.out.println(literalString.equals(textBlockString)); // true

        // ERROR
        /* String name = """Omer Soydemir"""; */

        // ERROR
        /* String colors = """red
                 green
                 blue
                 """; */

        // Multiline example
        String oldMultiline = "In the sad moonlight, \n" +
                "she clasped him by the neck, \n" +
                "and laid her face upon his breast. ";
        System.out.println(oldMultiline);

        String newMultiline = """
                In the sad moonlight,
                she clasped him by the neck,
                and laid her face upon his breast.
                """;
        System.out.println(newMultiline);

        // JSON object with text blocks
        System.out.println(getResultAsJson());

        // SQL blocks
        System.out.println(getSQLQuery());

    }

    private static String getSQLQuery() {
        return """
                SELECT *
                FROM customers
                WHERE first_name = 'Joe'
                ORDER BY last_name ASC;
                """;
    }

    private static String getResultAsJson() {
        return """
                {
                  "id": 1235,
                  "title": "Example Product",
                  "description": "A product which is nothing like apple",
                  "price": 549
                }
                """;
    }

}

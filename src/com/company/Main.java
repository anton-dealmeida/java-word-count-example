package com.company;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int menu = Integer.parseInt(JOptionPane.showInputDialog("Pick a option and enter your word or sentence \n 1. Word \n 2. Sentence", 1));

        switch (menu) {
            case 1:
                String word = getUserInput("word");
                String firstLetter = getFirstLetter(word),
                        lastLetter = getLastLetter(word),
                        middleLetter = getMiddleLetter(word),
                        uniqueLettersInWord = getUniqueLettersInWords(word),
                        uniqueVowels = getUniqueVowels(word, true),
                        reversedWord = getReversed(word);
                int uniqueVowelCount = countUniqueVowels(word);

                JOptionPane.showMessageDialog(
                        null,
                        "The first letter of your word is: \t" + firstLetter + "\n" +
                                "The last letter of your word is: \t"+ lastLetter +
                                "\n" +
                                "The middle letter in your word is: \t"+ middleLetter +
                                "\n" +
                                "Your word backwards is: \t" + reversedWord +
                                "\n" +
                                "The amount of unique vowels in your word is: \t" + uniqueVowelCount +
                                "\n" +
                                "The vowels in your word are: \t" + uniqueVowels +
                                "\n" +
                                "The unique letters in your word is: \t" + uniqueLettersInWord
                );
                break;
            case 2:
                String sentence = getUserInput("sentence");
//                JOptionPane.showMessageDialog(
//                        null,
//                        "The number of words in your sentence is:\n " +countWords+ "\n\n" +"Your sentence backwards is:\n " +Backwards+ "\n\n" +"These are the following vowels in your sentence:\n " +vowels+ "\n\n" +"This is your sentence with all the same consecutive letters removed:\n "+removeLetters);
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                menu = Integer.parseInt(JOptionPane.showInputDialog("Please pick a valid option and enter your word or sentence \n 1. Word \n 2. Sentence"));
        }
    }

    public static int countLetters(String userInput) {
        int count = 0;

        for (int i = 0; i < userInput.length(); i++) {
            if(Character.isLetter(userInput.charAt(i))) {
                count ++;
            }
        }
        return count;
    }

    public static int countUniqueVowels(String userInput) {
        int count = getUniqueVowels(userInput).length();
        return count;
    }

    public static int countWords(String userInput) {
        int count = 0;

        if (userInput.length() < 2) {
            System.out.println("No word or sentence provided, only got: " + userInput);
            return count;
        }

        for (int i = 0; i < userInput.length(); i++) {
            if(!Character.isLetter(userInput.charAt(i)) &&
                    ( userInput.charAt(i) == ' ' && userInput.charAt(i + 1) != ' ' )
            ) {
                count ++;
            }
        }

        count++; // For the first word in the sentence.
        return count;
    }

    public static String getFirstLetter(String userInput) {
        String letters = getLetters(userInput);
        return String.valueOf(letters.charAt(0));
    }

    public static String getUserInput(String inputType) {
        return JOptionPane.showInputDialog("Please enter your " + inputType + " here:");
    }

    public static String getLastLetter(String userInput) {
        String letters = getLetters(userInput);
        return String.valueOf(letters.charAt(letters.length() - 1));
    }

    public static String getLetters(String userInput) {
        String letters = "";

        for (int i = 0; i < userInput.length(); i++) {
            if(Character.isLetter(userInput.charAt(i))) {
                letters += userInput.charAt(i);
            }
        }

        return letters;
    }

    public static String getMiddleLetter(String userInput) {
        String letters = getLetters(userInput);
        // T r i s t a n
        // 0 1 2 3 4 5 6 Length: (7 + 1) / 2 = 4
        //               Length: round((7 / 2) - 1 = 2.5) = 3

        if (!(letters.length() % 2 == 0)) {
            return String.valueOf(letters.charAt(Math.round( ((float)letters.length() / 2) - 1 )));
        }

        // M a r k
        // 0 1 2 3   Length: 4 / 2 = 2
        // M a r k u s
        // 0 1 2 3 4 5 Length: 6 / 2 - 1 = 2
        return letters.substring(letters.length() / 2 - 1, letters.length() / 2 + 1);
    }

    public static String getReversed(String userInput) {
        String reversedString = "";

        for (int i = userInput.length() - 1; i >= 0 ; i--) {
            reversedString += userInput.charAt(i);
        }
        
        return reversedString;
    }

    public static String getUniqueLettersInWords(String userInput) {
        // Hello, World!
        String[] words = userInput.split("\\s+");
        // [0: "Hello,",1: "World!"]

        int wordCounter = 0;
        for (String word: words) {
            String temp = "";
            for (int i = 0; i < word.length(); i++) {
                String ch = String.valueOf((word.charAt(i)));
                if(!temp.contains(ch)) {
                    temp += ch;
                }
            }
            words[wordCounter] = temp;
            System.out.println(words[wordCounter]);
            wordCounter++;
        }

        return String.join(" ", words);
    }

    public static String getUniqueVowels(String userInput) {
        String vowels = "aeiou";

        // Error handling for idiots <3
        if (userInput.length() < 2) {
            System.out.println("No word or sentence provided, only got: " + userInput);
            return "";
        }

        String vowelsFound = "";

        for (int i = 0; i < userInput.length(); i++) {
            String character = String.valueOf(userInput.charAt(i));
            if (vowels.contains(character)) { // is this character a vowel ( yes | no )?
                if (!vowelsFound.contains(character)) { // if I have not found the vowel ( yes | no )?
                    vowelsFound += character; // put the vowel I found in the vowelsFound placeholder.
                }
            }
        }

        return vowelsFound;
    }

    public static String getUniqueVowels(String userInput, Boolean formatString) {
        String vowels = getUniqueVowels(userInput);
        String formattedVowels = "";
        String formatSymbol = ", ";

        // If formatString = false, don't format the string
        if (!formatString) {
            return vowels;
        }

        for (int i = 0; i < vowels.length(); i++) {
            formattedVowels += vowels.charAt(i);

            if (i != vowels.length() - 1) {
                formattedVowels += formatSymbol;
            }
        }

        return formattedVowels;
    }
}
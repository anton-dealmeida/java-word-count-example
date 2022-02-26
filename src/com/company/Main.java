package com.company;

import java.io.*;
import java.awt.FlowLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
    public static void main(String[] args) {
        // this static declaration can be replaced with an input.
        String mySentence = "Hello, World!";

        // Sadly, Java does not have pretty string interpolation.
        System.out.println(countWords(mySentence) + " words");

        // You can also use System.in to read stuff from the console.
        // This won't work if you execute this using javaw or Eclipse/IntelliJ IDEA, NetBeans, etc..
        // You'll need to execute the app from a cmd or console for this to work and can't use javaw like the IDEs do.
        // To run the above, you need to build/compile your code, then open the terminal and run:
        // java -cp
        // Example 1:
//        Console cons = System.console();
//
//        if (cons == null) {
//            System.out.println("No console available");
//            return;
//        }
//
//        // Read line from console.
//        String userInput = cons.readLine("Enter string: ");
//
//        // Let's print that string...
//        System.out.println("You entered: " + userInput);

        // Alternatively, using Console in IDEs might need some magic...
        // See: https://stackoverflow.com/questions/26470972/trying-to-read-from-the-console-in-java
        Scanner scanner = new Scanner(System.in); // This uses java.util.Scanner, a built in library.

        String sentence = scanner.nextLine();
        System.out.println(countWords(sentence) + " words");
        System.out.println(countDistinctVowels(sentence) + " unique vowels");
        System.out.println(countDistinctVowelsv2(sentence) + " unique vowels using version 2D");
        System.out.println("The vowels are: " + getDistinctVowels(sentence));
        System.out.println("The sentence reversed is: " + reverseSentence(sentence));
        System.out.println(countLetters(sentence) + " letters");

        /*
         Example 2: This one works in IDEs...
         I don't like it, it needs special imports on line 4 ~ 6
         This will need the use of JFrame, eeww
        */
//        JFrame frame = new JFrame("My Word App");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new FlowLayout());
//        frame.setSize(250, 100);
//
//        frame.add(new JLabel("Enter a sentence"));
//        String code = JOptionPane.showInputDialog(frame, "enter a sentence");
//        frame.setVisible(true);
//
//        System.out.println(code);
    }

    /*
        Count Words Method
        This will return the amount of words in the string provided to it.
        Also note "static" between public and int, it's important.
        That static means this is a property that can be called freely and does not have to be instantiated.
        What that means is, I don't have to say: int count = new count_words(sentence);
        So, I can call this method directly within my System.out.println like I do in main.
     */
    public static int countWords(String sentence) {
        // declaring our counter variable at the root scope of count_words
        // so that I can return from outside of the for loop.
        int count = 0;

        // declaring an array of chars, I'll be feeding my sentence into it.
        char[] ch = new char[sentence.length()];

        // for each character in my sentence, I will do stuff...
        for (int i = 0; i < sentence.length(); i++) {

            // Adding the character at the i position of my sentence to the
            // i position in my char array.
            ch[i] = sentence.charAt(i);

            // If i is not currently on the first letter and i is not a blank space
            // and the previous spot is not a blank space then add 1 to my word count.
            // otherwise, if spot 0 is not blank and i is currently 0 add a word.
            if (((i > 0) && (ch[i] != ' ') && (ch[i - 1] == ' ')) || ((ch[0] != ' ') && (i == 0))) {
                count++;
            }
            /*
                Some extra info about the above line of code.
                If I'm counting all the spaces in a sentence, I am roughly counting the words in that sentence,
                because between every word in my sentence, there has to be a space.
                However, there is not a space in front of the first word, so I will count it.
                But, I need to think about - what if an idiot adds a space there anyway?
                So, the above if statement is a little complicated as it is checking many conditions.
             */
        }

        // I can return count here, because it's not scoped within the for loop.
        return count;
    }

    /*
        Reverse Sentence Method
        This method takes in a String object and reverses it using a for loop that counts backwards.
     */
    public static String reverseSentence(String sentence) {
        // To ensure we can return the string after our for loop, we must declare an empty String to be a placeholder.
        String reversedSentence = "";

        // Take the length of your sentence, subtract one (because length is not zero based here) and count in reverse.
        for (int i = sentence.length() - 1; i > 0; i--) {
            // this += operator, allows us to append whatever is on the right of the += to whatever is on the left.
            // append means to add at the end. (Prepend means to add in front.)
            reversedSentence += sentence.charAt(i);
        }

        return reversedSentence;
    }

    /*
        Count Letters Method
        This method will count the amount of alphanumeric letters there are in a sentence.
     */
    public static int countLetters(String sentence) {
        // declaring an empty int and setting it to 0 so that we can count upwards from 0.
        // We can't increment or decrement if the computer does not know from what number to go up or down from.
        int count = 0;

        // For every character in the sentence (use String.length() method)
        for (int i = 0; i < sentence.length(); i++) {
            // If, the character in the sentence at position i is a letter, count it.
            if (Character.isLetter(sentence.charAt(i))) {
                count++;
            }
        }

        return count;
    }

    /*
        Count Distinct Vowels Method
        This method will count the amount of distinct vowels are present in the sentence.
        This method will return an integer between 0 and 5.
     */
    public static int countDistinctVowels(String sentence) {
        // First, we make everything lower case so that we only check for 1 set of ASCII values.
        sentence = sentence.toLowerCase();

        // Second, declare our counter and set it to zero so we can increment / decrement if necessary.
        int count = 0;

        // Thirdly, there are 2 ways to do this, 1 can use the Java-8 way, the other is a manual for loop.
        // Now, the first method - also the more complicated Java-8 way method but easiest to read.
        count = (int) sentence.chars()// get an IntStream of the chars in the string.
                .mapToObj(c -> (char) c) // cast each item in the string array to charWW
                .filter(c -> "aeiou".indexOf(c) > -1) // remove every non-vowel
                .distinct() // remove duplicates (keep only unique values)
                .count(); // count what's left over

        // I commented the below out, as they're explanatory.
//        // Last, we have the second method, more code and also efficient and probably the one you should use.
//        // Declare a check for each of the vowels to check if they're there or not.
//        // We could make them numbers too... I'm making them numbers.
//        // boolean a = false, e = false, i = false, o = false, u = false;
//        int a = 0, e = 0, i = 0, o = 0, u = 0;
//
//        for (char ch : sentence.toLowerCase().toCharArray()) {
//            if (ch == 'a') {
//                a = 1;
//            } else if (ch == 'e') {
//                e = 1;
//            } else if (ch == 'i') {
//                i = 1;
//            } else if (ch == 'o') {
//                o = 1;
//            } else if (ch == 'u') {
//                u = 1;
//            }
//        }
//        // Sum them up. :)
//        count = a + e + i + o + u;
//
//        // Say, I had to return "which" vowels were present instead of how many of the 5 were present, I'd add some more.
//        // This next check would basically add the vowel to a string if it's present.
//        // Declare vowel container, and initialize it as empty so that we can use += on it.
//        String vowels = "";
//        if (a == 1) {
//            vowels += 'a';
//        } else if (e == 1) {
//            vowels += 'e';
//        } else if (i == 1) {
//            vowels += 'i';
//        } else if (o == 1) {
//            vowels += 'o';
//        } else if (u == 1) {
//            vowels += 'u';
//        }
//
//        // Then, return the string - however, this method is an int, so it will not allow you to do the below here.
//        // So, let's add the code above to a different method.
//        // return vowels;

        // Return amount of unique vowels in here.
        return count;
    }

    // If you are truly lazy, this version 2 of countDistinctVowels is for you.
    public static int countDistinctVowelsv2(String sentence) {
        return getDistinctVowels(sentence).length();
    }
    /*
        Get Distinct Vowels
        This method will return a string representation detailing which vowels were found in the string provided.
        The return value will be a String object between 0 and 5 characters long.
     */
    public static String getDistinctVowels(String sentence) {
        // Take my sentence, change all my letters to lower case letters and change my sentence into a char array.
        // Assign the above to ch.
        sentence = sentence.toLowerCase();

        String vowels = "";

        // Going through the array of characters, we will now use a case statement instead of a string of if statements.
        for (int i = 0; i < sentence.length(); i++) {
            String ch = String.valueOf(sentence.charAt(i)); // Make a new string with the String value of the character at position i of the sentence.
            if ("aeiou".contains(ch)){ // if the String "aeiou" contains the current character of the sentence, append it to the vowel string.
                if (!vowels.contains(ch)) // if the vowels String does not contain the new vowel, append it to the string.
                    vowels += ch;
            }
        }
        /*
            Caveat for above, this is a cleaner way of doing it and has less if statements, also does not use any case statements.
            The code will check IF the individual character of a String is contained within the string "aeiou".
         */

        return vowels;
    }
}
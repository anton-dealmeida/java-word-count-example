package com.company;

public class Main {
    public static void main(String[] args) {
        // this static declaration can be replaced with an input.
        String mySentence = "Hello, World!";

        // Sadly, Java does not have pretty string interpolation.
        System.out.println(count_words(mySentence) + " words");
    }

    /*
        This property will return the amount of words in the string provided to it.
        Also note "static" between public and int, it's important.
        That static means this is a property that can be called freely and does not have to be instantiated.
        What that means is, I don't have to say: int count = new count_words(sentence);
        So, I can call this method directly within my System.out.println like I do in main.
     */
    public static int count_words(String sentence) {
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
            if( ((i > 0) && (ch[i] != ' ') && (ch[i - 1] == ' ')) || ((ch[0] != ' ') && (i == 0)) ) {
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
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Created by everette on 4/15/16.
 */
public class Main {

    // a method to return a hashmap of the number of times a char appears in
    // a string.
    static HashMap<Character, Integer> frequencies(String input) {
        if(input == null || input.isEmpty()) {
            return null;
        }

        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();

        // iterate on the characters in the given string.
        // chracters in the string are used as keys.
        for(char c : input.toCharArray()) {
            if(hashMap.containsKey(c)) { // if the key exists, add one to the frequency
                hashMap.put(c, hashMap.get(c) + 1);
            } else {
                // otherwise, give that key an initial value of one.
                hashMap.put(c, 1);
            }
        }

         return hashMap;
    }

    public static void main(String[] args) {
        try {
            // load the words.txt found on the Codefoo website.
            FileReader file = new FileReader("words.txt");

            // create a buffer to iterate on.
            BufferedReader bufferedReader = new BufferedReader(file);

            // create a scanner for the user to give a list of letters they have.
            Scanner kb = new Scanner(System.in);
            System.out.println("Enter a list of letters in the following formats:\n\"a x e t i\" or \"axeti\"");

            // strip all spaces from the input and lower case it all.
            String letters = kb.nextLine().replaceAll(Pattern.compile("\\s+").toString(), "").toLowerCase();

            // get the frequency of each letter given.
            HashMap<Character, Integer> letterFreq = Main.frequencies(letters);

            // a list for all words accepted by the initial algorithm.
            ArrayList<String> acceptedWords = new ArrayList<String>();
            String word = ""; // string to hold the current word evaluated.

            while((word = bufferedReader.readLine()) != null) {
                // if we have less letters than the word has, ignore it.
                // Can't make apple with three letters.
                if(letters.length() < word.length()) {
                    continue;
                } else {
                    // get the frequency of the characters in the word
                    HashMap<Character, Integer> charFreq = Main.frequencies(word);

                    // iterate on each character in the word to find if the word
                    // contains a letter we don't have, or if don't have enough of
                    // that letter.
                    boolean acceptedWord = true;
                    for(char c : word.toCharArray()) {
                        if(letters.indexOf(c) == -1 || charFreq.get(c) > letterFreq.get(c)) {
                            acceptedWord = false;
                            break;
                        }
                    }

                    // if the above iteration didn't find the word to be
                    // invalid, add the word to the list of acceptable words.
                    if(acceptedWord) {
                        acceptedWords.add(word);
                    }
                }
            }

            // hashmap of all different points and their associated characters.
            // will use keys to assign points.
            HashMap<Integer, String> letterPoints = new HashMap<Integer, String>();

            letterPoints.put(1, "aeiolnstr");
            letterPoints.put(2, "dg");
            letterPoints.put(3, "bcmp");
            letterPoints.put(4, "fhwy");
            letterPoints.put(5, "k");
            letterPoints.put(8, "jz");
            letterPoints.put(10, "qz");

            // what is the highest points given for a word already?
            int highestPoints = 0;
            String bestWord = ""; // storage for the best word we have.

            for(String accepted : acceptedWords) {
                // always zero at start because this is a new word.
                int points = 0;
                // look at characters in the word to get the points awarded for it.
                for(char acceptedChar : accepted.toLowerCase().toCharArray()) {
                    for(int p : letterPoints.keySet()) { // get the key of the associated character
                        if(letterPoints.get(p).indexOf(acceptedChar) != -1) {
                            points += p; // add to the total points the key value of the hash.
                            break;
                        }
                    }
                }

                // if the points accrued is higher than the last word
                // we got high points for, make that our new best word.
                // will always set the first word from all accepted words
                // to the best word. Then it will be replaced by the first
                // best word after.
                if(points > highestPoints) {
                    bestWord = accepted;
                    highestPoints = points;
                }
            }

            // tell the user that they can use their letters to make the now found best word
            // and what those points will be.
            System.out.println("The best word you can make with your letters you have is \"" +
                    bestWord + "\" with a score of " + highestPoints + ".");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

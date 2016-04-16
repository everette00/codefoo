import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Character, Character> azertyNonAlpha = new HashMap<Character, Character>();
        String azertyAlpha = "AZERTYUIOPQSDFGHJKLWXCVBNazertyuiopqsdfghjklwxcvbn";
        String qwertyAlpha = "QWERTYUIOPASDFGHJKLZXCVBNqwertyuiopasdfghjklzxcvbn";

        // There is a lot below, so I will give a general description of what I did here.
        // each character on the azerty keyboard maps directly to a key on the qwerty keyboard.
        // I created a hash map of character to character for every mapping. Some characters
        // from AZ -> QW are in hex, as they are not natural characters on a standard US keyboard.
        // Also, some keys have three mappings to them, so some keys have three translations (for example,
        // a double-quote and a hashkey map to the letter three - (", #)-> 3).
        // Lastly, I created string of characters that map all AZERTY alpha chars to QWERTY, minus the M key,
        // as that was the only alpha key on the AZ keyboard that was misplaced with a special character key.
        // I have more comments below the mess of hashmap puts.

        azertyNonAlpha.put((char)0xb2, '`');
        azertyNonAlpha.put((char)(0x0f & 0xb2), '~');

        azertyNonAlpha.put('&', '1');
        azertyNonAlpha.put('1', '!');

        azertyNonAlpha.put((char)0xe9, '2');
        azertyNonAlpha.put('2', '@');
        azertyNonAlpha.put('~', '2');

        azertyNonAlpha.put('"', '3');
        azertyNonAlpha.put('3', '#');
        azertyNonAlpha.put('#', '3');

        azertyNonAlpha.put('\'', '4');
        azertyNonAlpha.put('4', '$');
        azertyNonAlpha.put('{', '4');

        azertyNonAlpha.put('(', '5');
        azertyNonAlpha.put('5', '%');
        azertyNonAlpha.put('[', '5');

        azertyNonAlpha.put('-', '6');
        azertyNonAlpha.put('6', '^');
        azertyNonAlpha.put('|', '6');

        azertyNonAlpha.put((char)0xe8, '7');
        azertyNonAlpha.put('7', '&');
        azertyNonAlpha.put('`', '7');

        azertyNonAlpha.put('_', '8');
        azertyNonAlpha.put('8', '*');
        azertyNonAlpha.put('\\', '8');

        azertyNonAlpha.put((char)0xe7, '9');
        azertyNonAlpha.put('9', '(');
        azertyNonAlpha.put('^', '9');

        azertyNonAlpha.put((char)0xe0, '0');
        azertyNonAlpha.put('0', ')');
        azertyNonAlpha.put('@', '0');

        azertyNonAlpha.put(')', '-');
        azertyNonAlpha.put((char)0xba, '_');
        azertyNonAlpha.put(']', '-');

        azertyNonAlpha.put('=', '=');
        azertyNonAlpha.put('+', '+');
        azertyNonAlpha.put('}', '=');

        azertyNonAlpha.put('<', '<');
        azertyNonAlpha.put('>', '>');

        azertyNonAlpha.put(',', 'm');
        azertyNonAlpha.put('?', 'M');

        azertyNonAlpha.put(';', ',');
        azertyNonAlpha.put('.', '<');

        azertyNonAlpha.put(':', '.');
        azertyNonAlpha.put('/', '>');

        azertyNonAlpha.put('!', '/');
        azertyNonAlpha.put((char)0xa7, '?');

        azertyNonAlpha.put('m', ';');
        azertyNonAlpha.put('M', ':');

        azertyNonAlpha.put((char)0xf9, '\'');
        azertyNonAlpha.put('%', '"');

        azertyNonAlpha.put('*', '\\');
        azertyNonAlpha.put('*', '|');


        // here is the translation portion of the code.
        String input = "", translation = ""; // strings to store user input and the translation.
        System.out.println("Type something in of the AZERTY type. When finished, type \"O\'doyal rules\".");

        Scanner sc = new Scanner(System.in); // build a scanner for the user to give input.

        // check to see if the given input is not "O'doyal rules" with no case-sensitivity.
        while(!(input = sc.nextLine()).equalsIgnoreCase("O\'doyal rules")) {
            if(input.toLowerCase().contains("h")) { // Check to see if we have an 'H' in the string. The 'H' key sticks.
                input = input.replaceAll("[H|h]{2,}", "H"); // Leave all but one sequential series of 'H'. "HHHHHH" -> "H"
            }
   
            // iterate on all characters in the input and add to the
            // translation the equivalent characters on a qw keyboard.
            for(char c : input.toCharArray()) {
                if(azertyNonAlpha.containsKey(c)) { // check the hash first.
                    // get that character from the has assigned to the key from the az board.
                    translation += azertyNonAlpha.get(c);
                } else {
                    // otherwize, get the key from the alpha set.
                    translation += qwertyAlpha.charAt(azertyAlpha.indexOf(c));
                }
            }
            
            // the scanner locks the while loop, so we can append a line end to support multi-line text.
            translation += "\r\n";
        }
        
        // now print the final translation out.
        System.out.println("Translated text: \n" + translation);
    }
}

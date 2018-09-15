import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Kareith Dyce (kzd0030@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2017-04-28
 */
public class Doublets implements WordLadderGame {

    ////////////////////////////////////////////
    // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
    ////////////////////////////////////////////

    // A word ladder with no words. Used as the return value for the ladder methods
    // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;


    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next().toLowerCase();
            lexicon.add(str);
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }
    /**
    * @param  str1 the first string.
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */

   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
   
      int ham = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            ham++;
         }
      }
      return ham;
   }


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
   
      ArrayDeque<String> look = new ArrayDeque<String>();
      List<String> storage = null;
      look.addLast(start);
      ArrayList<String> visited = new ArrayList<String>();
      visited.add(start);
      ArrayList<String> store = new ArrayList<String>();
      store.add(start);
      if (start.equalsIgnoreCase(end)) {
         return store;
      }
      int ham0 = getHammingDistance(start, end);
      int ham = ham0;
      while (!look.isEmpty()) {
         String close = look.removeFirst();
         List<String> near = getNeighbors(close);
         for (String s : near) {
            if (near.contains(end)) {
               store.add(end);
               if (!isWordLadder(store)) {
                  return rearange(store);
               }
               return store;
            }
         
            if (!visited.contains(s)) {
               visited.add(s);
               if (getHammingDistance(s, end) < ham) {
                  String last = store.get(store.size() - 1);
               
                  if (getHammingDistance(last, s) != 1) {
                     storage = getNeighbors(s);
                     for (String str :storage) {
                        if(getHammingDistance(last, str) == 1){
                           store.add(str);
                           break;
                        }
                     }
                  }
               
               
                  ham = getHammingDistance(s, end);
                  store.add(s);
               }
               look.addLast(s);
            }
         
         }
      }
   
      if (!store.contains(end)) {
         return EMPTY_LADDER;
      }
   
      return store;
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      word = word.toLowerCase();
      List<String> near = new LinkedList<String>();
      for (String s: lexicon) {
         if (getHammingDistance(word, s) == 1) {
            near.add(s);
         }
      }
      return near;
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty() || !isWord(sequence.get(0))) {
         return false;
      }
      for (int i = 1; i < sequence.size(); i++) {
         if (!isWord(sequence.get(i))) {
            return false;
         }
         if (getHammingDistance(sequence.get(i - 1), sequence.get(i)) != 1) {
            return false;
         }
      }
      return true;
   }

   private List<String>rearange(List<String> sequence) {
      List<String> real = new ArrayList<String>();
      for (int i = 1; i < sequence.size(); i++) {
         if(getHammingDistance(sequence.get(i - 1), sequence.get(i)) != 1){
            List<String> temp1 = getMinLadder(sequence.get(i - 1), sequence.get(i));
            for (int j = 0; j < i - 1; j++) {
               real.add(sequence.get(j));
            }
            for (String s : temp1) {
               real.add(s);
            }
         
            for(int k = i + 1;  k < sequence.size(); k++){
               real.add(sequence.get(k));
            }
            return real;
         }
      }
      return null;
   }

}

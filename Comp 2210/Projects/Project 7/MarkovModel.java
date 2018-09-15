import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Kareith Dyce (you@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2017-11-28
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;
   private Random ran = new Random();
   private String first;

   // add other fields as you need them ...


   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**q
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
   
      for(int i = 0; i + K < sourceText.length(); i++) {
         String str = sourceText.substring(i, i+K);
         if(model.isEmpty()){
            first = str;
         }
         if(model.containsKey(str)){
            continue;
         }
         String next = "";
         for(int j = 0;  j + K + 1 < sourceText.length();) {
            int look = sourceText.indexOf(str, j);
            if(look < 0 || look + K + 1 > sourceText.length()) {
               break;
            }
            j = look+1;
            look += K;
            next += sourceText.substring(look, look+1);
         }
         model.put(str,next);
      
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return first;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      Set<String> s = getAllKgrams();
      int random = ran.nextInt(s.size());
      int i = 0;
      for(String str : s){
         if(i == random){
            return str;
         }
         i++;
      }
      return null;
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      return kgram.charAt(ran.nextInt(kgram.length()));
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}

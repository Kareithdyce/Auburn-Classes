import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;


/**
 * Defines the methods needed to play a word search game.
 *
 * @author Kareith Dyce (kzd0030@auburn.edu)
 * @version 2017-03-28
 *
 */
public class WordGame implements WordSearchGame {
   private String[][] gameBoard = {{"E", "E","C","A"}, {"A", "L", "E", "P"},
                                   {"H", "N","B","O"}, {"Q", "T", "T", "Y"}};
   private boolean lexiconLoaded = false;
   private TreeSet<String> gameLexicon;
   private int position;
   private int length = 4;
   private int more  = 0;
   private Stack<String> storeWords;

   // number of neighbors, degrees of motion
   private static final int MAX_NEIGHBORS = 8;
   /**
    * Loads the lexicon into a data structure for later use.
    *
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */

   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      gameLexicon = new TreeSet<String>();
      try {
         Scanner s =
            new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (s.hasNext()) {
            String str = s.next().toLowerCase();
            gameLexicon.add(str);
            s.nextLine();
            lexiconLoaded = true;
         }
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
   }
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    *
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */

   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      if (Math.sqrt(letterArray.length) % 1 != 0) {
         throw new IllegalArgumentException();
      }
   
      length = (int)Math.sqrt(letterArray.length);
      String[][] temp = new String[length][length];
      int index = 0;
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            temp[i][j] = letterArray[index++];
         }
      }
      gameBoard = temp;
   
   }

   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
      return Arrays.deepToString(gameBoard);
   }   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    *
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */

   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!lexiconLoaded) {
         throw new IllegalStateException();
      }
      SortedSet<String> words = new TreeSet<String>();
      for (String str : gameLexicon ) {
         if (str.length() >= minimumWordLength && !isOnBoard(str).isEmpty()) {
            words.add(str);
         }
      }
   
      return words;
   }

  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */

   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      int score = 0;
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!lexiconLoaded) {
         throw new IllegalStateException();
      }
      for (String str : words) {
         if (str.length() >= minimumWordLength && isValidWord(str)
            && !isOnBoard(str).isEmpty()) {
            score += (str.length() - minimumWordLength) + 1;
         }
      }
      return score;
   }

   /**
    * Determines if the given word is in the lexicon.
    *
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!lexiconLoaded) {
         throw new IllegalStateException();
      }
      return gameLexicon.contains(wordToCheck.toLowerCase());
   }

   /**
    * Determines if there is at least one word in the lexicon with the
    * given prefix.
    *
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!lexiconLoaded) {
         throw new IllegalStateException();
      }
      prefixToCheck = prefixToCheck.toLowerCase();
      return gameLexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }

   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
   
      if (!lexiconLoaded) {
         throw new IllegalStateException();
      }
   
      wordToCheck = wordToCheck.toLowerCase();
      Stack<Integer> num = new Stack<Integer>();
      Position[][] visited = new Position[length * length][MAX_NEIGHBORS];
      String check = wordToCheck;
      Position p = null;
      if (findFirst(check, 0)) {
         p = new Position(getX(position), getY(position));
         visited[position] = p.neighbors();
         num.push(position);
      }
   
      if (p == null) {
         return num;
      }
      int storeFirst = position + 1;
   
      for (int i = more; i < wordToCheck.length(); i += more) {
         check = wordToCheck.substring(i);
         p = getFirstUnvisited(visited[position], check, num);
         if (p != null) {
            num.push(position);
            visited[position] = p.neighbors();
            continue;
         }
         if (num.size() >= 2) {
            num.pop();
            position = num.peek();
            i -= more + 1;
            continue;
         }
      
         if (num.size() <= 1) {
            check = wordToCheck;
            if (findFirst(check, storeFirst)) {
               i = 0;
               num.pop();
               p = new Position(getX(position), getY(position));
               visited[position] = p.neighbors();
               num.push(position);
               storeFirst = position + 1;
            }
            else {
               num.clear();
               return num;
            }
         }
      }
      return num;
   }

  /*
  * Searchs The board for a letter.
  * @throws IllegalArgumentException if wordToCheck is null.
  */
   private boolean findFirst(String letter, int index) {
      if (letter == null) {
         throw new IllegalArgumentException();
      }
   
      letter = letter.toLowerCase();
      position = 0;
      for (int i = index; i < length * length; i++) {
         if (letter.startsWith(gameBoard[getY(i)][getX(i)].toLowerCase())) {
            more = gameBoard[getY(i)][getX(i)].length();
            position = i;
            return true;
         
         
         }
      }
      return false;
   }


  /**
  * Searchs The board for a letter nearby.
  */
   public Position getFirstUnvisited(Position[] positions, String letter,
      Stack<Integer> num) {
      for (Position pos : positions) {
         if (!pos.visited && letter.startsWith(gameBoard[pos.y][pos.x].toLowerCase())
            && !num.contains(convert2Dto1D(pos.x, pos.y))) {
            pos.visited = true;
            more = gameBoard[pos.y][pos.x].length();
            position = convert2Dto1D(pos.x, pos.y);
            return pos;
         }
      }
      return null;
   }

  /** Gets the x coordinate.*/

   private int getX(int pos) {
      return pos % length;
   }

  /** Gets the y coordinate.*/

   private  int getY(int pos) {
      return pos / length;
   }


  /**
  * Converts the 2D coordinate into a 1D coordinate.
  */
   private int convert2Dto1D(int x, int y) {
      int pos =  x + (y * length);
      return pos;
   }

   private class Position {
      int x;
      int y;
      boolean visited;
   
     /** Constructs a Position with coordinates (x,y). */
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
         visited = false;
      }
   
   
   
     /** Returns all the neighbors of this Position. */
      public Position[] neighbors() {
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
        // generate all eight neighbor positions
        // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }

   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < length) && (p.y >= 0) && (p.y < length);
   }



}

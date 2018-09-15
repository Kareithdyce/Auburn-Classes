import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Stack;

public class WordGameTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      String [] board = {"O","Y","D","D","T","P","N","R","A","H","E","L","C","S","B","P","S","U","B","G","U","P","Y","H",
                      "R","R","X","R","E","F","H","D","H","T","K","X","K","O","Z","F","W","Y","H","Y","T","C","H","M",
                      "V","P","R","T","A","K","N","E","S","I","B","T","M","V","Y","Q","E","U","O","E","F","A","K","J",
                      "C","W","I","K","I","U","K","T","P","O","F","E","G","Z","T","X","O","Z","T","H","K","B","M","G",
                      "D","P","P","P","G","U","E","S","C","J","C","B","Q","F","T","R","I","P","N","I","E","W","P","K",
                      "H","K","G","B","B","L","Y","J","P","J","E","O","N","Q","V","N","B","S","H","R","N","Z","R","G",
                      "A","E","W","P","L","L","Z","R","G","I","E","T","U","N","R","L","I","K","T","J","K","J","F","C",
                      "I","T","M","R","D","T","R","E","G","L","J","G","I","K","H","L","C","V","P","P","D","S","Q","E",
                      "W","O","C","R","L","V","L","P","T","A","T","N","O","R","M","W","K","O","D","O","U","O","V","F",
                      "M","H","V","V","S","I","X","Z","L","O","T","Z","L","B","R","G","F","Q","P","A","Y","P","D","L",
                      "B","K","S","N","C","H","O","P","Y","K","H","C","R","R","I","C","S","B","J","X","R","F","I","Y",
                      "R","H","B","Z","I","P","C","K","I","N","O","E","C","C","U","C","P","I","J","R","E","Y","E","Z",
                      "U","R","R","M","F","S","M","R","N","J","I","B","T","Q","O","C","V","R","O","T","X","H","C","R",
                      "W","S","A","V","T","N","U","I","O","W","X","C","O","R","X","Q","A","S","A","S","S","E","M","B",
                      "L","Y","O","Z","F","P","L","S","C","I","T","L","U","M","O","N","I","T","O","R","J","W","I","N",
                      "L","L","L","E","L","J","R","R","E","M","M","O","B","D","X","I","J","D","S","R","L","C","H","S",
                      "H","Y","U","L","P","M","O","U","S","E","C","B","I","I","U","I",};
      WordGame g = new WordGame();
      g.setBoard(board);
      g.loadLexicon("wordfiles/words_medium.txt");
      g.getAllValidWords(10);   
      //assertEquals(g.getAllValidWords(10).size(), 1, 0);
   }
   
 
   
   @Test public void TigerTest1() {
      String [] board = {"TIGER",}; 
      WordGame g = new WordGame();
      g.setBoard(board);
      g.loadLexicon("wordfiles/words_medium.txt");
      assertEquals(g.getAllValidWords(3).size(), 1);
   }
   
   
   @Test public void TigerTest2() {
      String [] board = {"TIGER",}; 
      WordGame g = new WordGame();
      g.setBoard(board);
      g.loadLexicon("wordfiles/words_medium.txt");
      assertEquals(g.getAllValidWords(7).size(), 0);
   }
   
   @Test public void MoreLettersTest2() {
      String [] board = {"T","I","G","E"}; 
      WordGame g = new WordGame();
      Stack<Integer> num = new Stack<Integer>();
      num.add(0);
      num.add(1);
      num.add(2);
      num.add(3);
      g.setBoard(board);
      g.loadLexicon("wordfiles/words_medium.txt");
      assertEquals(g.isOnBoard("tige"), num);
   }
   
   @Test public void MoreLettersTest3() {
      WordGame g = new WordGame();
      Stack<Integer> num = new Stack<Integer>();
      num.add(5);
      num.add(6);
      num.add(9);
      num.add(13);
      g.loadLexicon("wordfiles/words_medium.txt");
      assertEquals(g.isOnBoard("lent"), num);
   }
   
   @Test public void MoreLettersTest4() {
      WordGame g = new WordGame();
      g.loadLexicon("wordfiles/words.txt");
      assertEquals(g.getAllValidWords(6).size(), 4);
   }

   @Test public void MoreLettersTest5() {
      String [] board = {"CAT","xxx", "FISH","x"}; 
      WordGame g = new WordGame();
      Stack<Integer> num = new Stack<Integer>();
      num.add(0);
      num.add(2);
      g.setBoard(board);
      g.loadLexicon("wordfiles/words_medium.txt");
      assertEquals(num, g.isOnBoard("catfish"));
   }
}

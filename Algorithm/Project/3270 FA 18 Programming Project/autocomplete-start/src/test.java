public class test{
    public static void main(String[] args) {
        double [] values = {6,4,2,3,5,7,1};
        String [] words = {"ape","app","ban","bat","bee","car","cat"};
        Autocomplete$TrieAutocomplete t = new Autocomplete.TrieAutocomplete(words,values);
        System.out.println("Top match of \"\" is correct: " + t.topMatch("").equals("car"));
        System.out.println("Top match of a is correct: " + t.topMatch("a").equals("ape"));
        System.out.println("Top match of ap is correct: " + t.topMatch("ap").equals("ape"));
        System.out.println("Top match of b is correct: " + t.topMatch("b").equals("bee"));
        System.out.println("Top match of ba is correct: " + t.topMatch("ba").equals("bat"));
        System.out.println("Top match of c is correct: " + t.topMatch("c").equals("car"));
        System.out.println("Top match of ca is correct: " + t.topMatch("ca").equals("car"));
        System.out.println("Top match of cat is correct: " + t.topMatch("cat").equals("cat"));
        System.out.println("Top match of d: is correct: " + t.topMatch("d").equals(""));
        System.out.println("Top match of \" \" is correct: " + t.topMatch(" ").equals(""));
        
        System.out.println("Top 8 matches of \"\" is correct: " + t.topMatches("", 8).toString().equals("[car, ape, bee, app, bat, ban, cat]"));
        System.out.println("Top 1 match of \"\" is correct: " + t.topMatches("", 1).toString().equals("[car]"));
        System.out.println("Top 2 matches of \"\" is correct: " + t.topMatches("", 2).toString().equals("[car, ape]"));
        System.out.println("Top 3 matches of \"\" is correct: " + t.topMatches("", 3).toString().equals("[car, ape, bee]"));
        System.out.println("Top 1 match of \"a\" is correct: " + t.topMatches("a", 1).toString().equals("[ape]"));
        System.out.println("Top 1 match of \"ap\" is correct: " + t.topMatches("ap", 1).toString().equals("[ape]"));
        System.out.println("Top 2 matches of \"b\" is correct: " + t.topMatches("b", 2).toString().equals("[bee, bat]"));
        System.out.println("Top 2 matches of \"ba\" is correct: " + t.topMatches("ba", 2).toString().equals("[bat, ban]"));
        System.out.println("Top 100 matches of \"d\" is correct: " + t.topMatches("d", 100).toString().equals("[]"));
    }
}
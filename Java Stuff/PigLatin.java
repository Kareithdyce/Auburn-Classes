import java.util.Scanner;
public class PigLatin{
    public static void main(String [] args){
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a phrase or word: ");
        String input = reader.nextLine();
        int find; 
        String translate = "";
        //find = input.indexOf(" ");

        do{
            find = input.indexOf(" ");
            String temp;
            if(find > 0){
                temp = input.substring(0,find);
                input = input.substring(find+1);
                System.out.println(input);
            }
            else{
                temp = input;
                System.out.println(temp);
           
            }
            translate += (" " +pigLatin(temp)); 

        }while(find != -1);
        translate = translate.trim();
        System.out.println(translate);
    }

    public static String pigLatin(String word){
        if(isAVowel(word.substring(0,1))){
            return word + "way";
        }
        do{
            word = word.substring(1) + word.substring(0,1);
        }
        while(!isAVowel(word.substring(0,1)));
        return word + "ay";  
    }
     public static boolean isAVowel(String letter){
        return "aeiou".contains(letter);
    }
}
package main.tricks;

public class StringTricks {

    public static void main(String[] args) {
        String src = "css";
        String[] words = src.split("\\s+");
        System.out.println(words.length);
        System.out.println(words[0]);
    }
}

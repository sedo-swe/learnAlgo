package main.tricks;

import java.util.Hashtable;

public class HashtableTricks {

    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();

        System.out.println(hashtable.get("Don't exists"));
        System.out.println(hashtable.get("Don't exists") == null);
    }
}

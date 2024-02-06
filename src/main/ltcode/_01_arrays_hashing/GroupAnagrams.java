package main.ltcode._01_arrays_hashing;

import java.util.*;

/**
 *  49. Group Anagrams (Medium)
 */
public class GroupAnagrams {

    public List<List<String>> groupingAnagramsSorting(String[] strs) {
        List<List<String>> result = null;

        Hashtable<String, ArrayList<String>> sets = new Hashtable<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String temp = new String(c);
            temp = temp.replaceAll(" ", "");
            if (sets.containsKey(temp)) {
                sets.get(temp).add(s);
            } else {
                sets.put(temp, new ArrayList<String>(
                        Arrays.asList(s)
                ));
            }
        }
        Enumeration<String> keys = sets.keys();
        if (sets.size() >= 0) {
            result = new ArrayList<List<String>>();
        }
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            result.add(sets.get(key));
        }
        return result;
    }

    public List<List<String>> groupingAnagramsLoop(String[] strs) {

        List<List<String>> result = null;

        Hashtable<String, List<String>> htable = new Hashtable<>();
        int[] key;
        int idx = -1;
        char[] cs;
        StringBuffer sb;
        List<String> temp;

        for (String s : strs) {
            key = new int[26];
            Arrays.fill(key, 0);
            cs = s.toCharArray();
            sb = new StringBuffer();

            for (int i = 0; i < cs.length; i++) {
                idx = cs[i] - 'a';
                key[idx]++;
            }

            for (int i = 0; i < 26; i++) {
                sb.append("#");
                sb.append(key[i]);
            }

            if (htable.containsKey(sb.toString())) {
                temp = htable.get(sb.toString());
            } else {
                temp = new ArrayList<>();
            }
            temp.add(s);
            htable.put(sb.toString(), temp);
        }

        Enumeration<String> keys = htable.keys();
        if (htable.size() >= 0) {
            result = new ArrayList<List<String>>();
        }
        while (keys.hasMoreElements()) {
            result.add(htable.get(keys.nextElement()));
        }

        return result;
    }

    private void runTest(String[] strs, String logic) {

        List<List<String>> res;
        if ("Sorting".equals(logic)) {
            res = this.groupingAnagramsSorting(strs);
        } else {
            res = this.groupingAnagramsLoop(strs);
        }

        if (res == null) {
            System.out.println("Empty result...");
            return;
        }

        for (List<String>  ss: res) {
            System.out.print("\t");
            for (String ele : ss) {
                System.out.print(ele + ", ");
            }
            System.out.println();
        }
    }

    public void testCasesSorting() {
        System.out.println("\nTest 1");
        String[] src1 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        this.runTest(src1, "Sorting");

        System.out.println("\nTest 2");
        String[] src2 = new String[]{""};
        this.runTest(src2, "Sorting");

        System.out.println("\nTest 3");
        String[] src3 = new String[]{"a"};
        this.runTest(src3, "Sorting");

        /*String[] ran = this.generateRandomStringArray();
        for (int i = 0; i < 10000; i++) {
            System.out.println(ran[i]);
        }*/

        System.out.println("\nTest 4");
        String[] src4 = new String[]{
                "act", "cat", "case", "aces", "note", "tone", "tar", "rat",
                "aide", "idea", "earth", "heart", "ours", "sour", "tea", "eat",
                "ape", "pea", "fast", "fats", "pat", "tap", "urn", "run",
                "are", "ear", "flow", "wolf", "pear", "reap", "use", "sue",
                "arm", "ram", "god", "dog", "pins", "spin", "war", "raw",
                "bare", "bear", "mars", "rams", "pots", "spots", "was", "saw",
                "beak", "bake", "meat", "team", "ring", "grin", "wed", "dew",
                "best", "bets", "meats", "steam", "sink", "skin", "who", "how",
                "boss", "sobs", "nap", "pan", "slip", "lips", "won", "now",
                "café", "face", "night", "thing", "tab", "bat", "yap", "pay",
                "care", "race", "atom", "bomb", "meals", "males", "saint", "satin",
                "avenge", "geneva", "meals", "salem", "sales", "seals",
                "balm", "lamb", "mean", "mane", "salts", "lasts",
                "blot", "bolt", "melon", "lemon", "salvages", "las vegas",
                "blow", "bowl", "moist", "omits", "sharp", "harps",
                "brag", "grab", "more", "rome", "shrub", "brush",
                "chum", "much", "needs", "dense", "siren", "rinse",
                "coal", "cola", "nerved", "denver", "skids", "disks",
                "counts", "tucson", "none", "neon", "skill", "kills",
                "diagnose", "san diego", "nude", "dune", "snail", "nails",
                "diary", "dairy", "ocean", "canoe", "sober", "robes",
                "domains", "madison", "pace", "cape", "soils", "oils",
                "dottier", "detroit", "pairs", "paris", "solo", "oslo",
                "fired", "fried", "pale", "leap", "spray", "prays",
                "fringe", "finger", "panels", "naples", "stack", "tacks",
                "hasten", "athens", "parks", "spark", "stick", "ticks",
                "iced", "dice", "pools", "spool", "strip", "trips",
                "inch", "chin", "ports", "sport", "study", "dusty",
                "keen", "knee", "posts", "stops", "team", "meat",
                "lamp", "palm", "races", "cares", "tooled", "toledo",
                "last", "salt", "reap", "pear", "votes", "stove",
                "limped", "dimple", "reef", "free", "waits", "waist",
                "lion", "loin", "robed", "bored", "wasps", "swaps",
                "looted", "toledo", "rock", "cork", "wells", "swell",
                "lump", "plum", "room", "moor", "west", "stew",
                "march", "charm", "ropes", "pores", "what", "thaw",
                "mash", "hams", "a’ decimal point", "i’m a dot in place", "monasteries", "amen stories",
                "a near miss", "an air miss", "old england", "golden land",
                "an aisle", "is a lane", "restaurant", "runs a treat",
                "considerate", "care is noted", "saintliness", "least in sins",
                "conversation", "voices rant on", "semolina", "is no meal",
                "dormitory", "dirty room", "signature", "a true sign",
                "dynamite", "may it end", "statue of liberty", "built to stay free",
                "eleven plus two", "twelve plus one", "the earthquakes", "that queer shake",
                "fourth of july", "joyful fourth", "the morse code", "here come dots",
                "gold and silver", "grand old evils", "the nudist colony", "no untidy clothes",
                "hms pinafore", "name for ship", "the tennis pro", "he in net sport",
                "limericks", "slick rime", "valentine poem", "pen mate in love",
                "departed this life", "he’s left it, dead; rip",
                "the public art galleries", "large picture halls, i bet",
                "year two thousand", "a year to shut down"
        };
        this.runTest(src4, "Sorting");
    }

    public void testCasesLoop() {
        System.out.println("\nTest 1");
        String[] src1 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        this.runTest(src1, "Loop");

        System.out.println("\nTest 2");
        String[] src2 = new String[]{""};
        this.runTest(src2, "Loop");

        System.out.println("\nTest 3");
        String[] src3 = new String[]{"a"};
        this.runTest(src3, "Loop");

        /*String[] ran = this.generateRandomStringArray();
        for (int i = 0; i < 10000; i++) {
            System.out.println(ran[i]);
        }*/

        System.out.println("\nTest 4");
        String[] src4 = new String[]{
                "auctioned", "cautioned", "education",
                "beastlier", "bleariest", "liberates", "cat",
                "cattiness", "scantiest", "tacitness",
                "countries", "cretinous", "neurotics", "tac",
                "cratering", "retracing", "terracing",
                "dissenter", "residents", "tiredness",
                "earthling", "haltering", "lathering", "act",
                "emigrants", "mastering", "streaming",
                "estranges", "greatness", "sergeants",
                "gnarliest", "integrals", "triangles",
                "mutilates", "stimulate", "ultimates",
                "reprising", "respiring", "springier"

        };
        this.runTest(src4, "Loop");
    }

    private String[] generateRandomStringArray() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String[] ranArr = new String[10000];
        for (int i = 0; i < ranArr.length; i++) {
            ranArr[i] = random.ints(leftLimit, rightLimit + 1)
                    .limit(random.nextInt(100))
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }
        return ranArr;
    }

    public static void main(String[] args) {

        GroupAnagrams ga = new GroupAnagrams();
//        ga.testCasesSorting();
        ga.testCasesLoop();
    }
}

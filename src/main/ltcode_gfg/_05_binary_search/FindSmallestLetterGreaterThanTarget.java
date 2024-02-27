package main.ltcode_gfg._05_binary_search;

/**
 * 744. Find Smallest Letter Greater Than Target
 * Easy
 */

@FunctionalInterface
interface FindNextGreatestLetter {
    char findNext(char[] letters, char target);
}

public class FindSmallestLetterGreaterThanTarget {
    FindNextGreatestLetter findNextGreatestLetter = ((letters, target) -> {
        // Do a binary search
        // If found,
        //  if it's the last, return first character
        //  if it's not the last, return right next character
        // If not found,
        // return the first character

        int size = letters.length - 1, start = 0, end = size;    // start 0, end = 0
        int mid = (start + end) / 2;                // (0 + 0) / 2 = 0
        while (start <= end) {
            if (target > letters[mid]) {            // target c, letters[mid] c
                // take right side
                start = mid + 1;
            } else if (target < letters[mid]) {
                // take left side
                end = mid - 1;
            } else {
                // found!
                if (mid < size) {
                    return letters[mid + 1];
                } else {
                    return letters[0];
                }
            }
            mid = (start + end) / 2;
        }
        return letters[0];
    });

    FindNextGreatestLetter findNextGreatestLetterSol = ((letters, target) -> {
        int size = letters.length - 1, start = 0, end = size;    // start 0, end = 2
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;                 // (0 + 0) / 2 = 0
            if (target >= letters[mid]) {            // target c, letters[mid] c
                // take right side
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return letters[start % size];
    });

    FindNextGreatestLetter findNextGreatestLetterSolution = ((letters, target) -> {
        int size = letters.length - 1, start = 0, end = size;
        int mid = 0;
        char nextLetter = letters[0];

        while (start <= end) {
            mid = (start + end) / 2;
            if (target < letters[mid]) {
                nextLetter = letters[mid];
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return nextLetter;
    });

    public void test(FindNextGreatestLetter func) {
        char[] test1 = {'c', 'f', 'j'};
        System.out.println(func.findNext(test1, 'a') + ", c");
        System.out.println(func.findNext(test1, 'c') + ", f");
        System.out.println(func.findNext(test1, 'd') + ", f");
        System.out.println(func.findNext(test1, 'j') + ", c");
        System.out.println(func.findNext(test1, 'k') + ", c");
        char[] test2 = {'x','x','y','y'};
        System.out.println(func.findNext(test2, 'z') + ", x");
        System.out.println(func.findNext(test2, 'x') + ", y");

    }

    public static void main(String[] args) {
        FindSmallestLetterGreaterThanTarget fs = new FindSmallestLetterGreaterThanTarget();
        System.out.println("** Initial Idea");
        fs.test(fs.findNextGreatestLetter);
        System.out.println("\n** First solution");
        fs.test(fs.findNextGreatestLetterSol);
        System.out.println("\n** Final solution");
        fs.test(fs.findNextGreatestLetterSolution);
    }
}

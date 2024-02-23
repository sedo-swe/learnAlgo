package main.ltcode_gfg._05_binary_search;

/**
 * 875. Koko Eating Bananas (Medium)
 */
@FunctionalInterface
interface IntKokoEatingBananas {
    int minEatingSpeed(int[] piles, int h);
}

public class KokoEatingBananas {
    /*
        Worked for base cases, failed due to "Memory Limit Exceeded"
     */
    IntKokoEatingBananas intKokoEatingBananasInitial = ((piles, h) -> {
        int k = 0;
        // find max value of piles
        int max = Integer.MIN_VALUE;
        for (int i : piles) {
            if (max < i)
                max = i;
        }
        int[] speed = new int[max];
        for (int i = 0; i < max; i++) {
            speed[i] = i + 1;
        }
        k = max;

        // Find k by using binary search on the array of speed
        int left = 0, right = max - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            // Check the speed of mid can eat all bananas
            int hours = 0;
            for (int i = 0; i < piles.length; i++) {
                hours += Math.ceil((double) piles[i] / speed[mid]);
            }
            if (hours <= h) {
                right = mid - 1;
                k = speed[mid];
            } else if (hours > h) {
                left = mid + 1;
            }
        }
        return k;
    });

    IntKokoEatingBananas intKokoEatingBananasNeet = ((piles, h) -> {
        // Initialize the left and right boundaries
        int left = 1, right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            int middle = (left + right) / 2;
            int hourSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / middle);
            }

            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return right;
    });

    IntKokoEatingBananas intKokoEatingBananasSol = ((piles, h) -> {
        int n = piles.length;
        long totalBananas = 0;
        for (int p : piles) {
            totalBananas += p;
        }
        int left = (int) ((totalBananas - 1) / h) + 1;
        int right = (int) ((totalBananas - n) / (h - n + 1)) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int time = 0;
            for (int p : piles) {
                time += (p - 1) / mid + 1;
            }
            if (time > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    });

    public void test(IntKokoEatingBananas func) {
        System.out.println("Expected: 4, Actual: " + func.minEatingSpeed(new int[]{3,6,7,11}, 8));
        System.out.println("Expected: 30, Actual: " + func.minEatingSpeed(new int[]{30,11,23,4,20}, 5));
        System.out.println("Expected: 23, Actual: " + func.minEatingSpeed(new int[]{30,11,23,4,20}, 6));
    }

    public static void main(String[] args) {
        KokoEatingBananas koko = new KokoEatingBananas();
        koko.test(koko.intKokoEatingBananasInitial);
        System.out.println();
        koko.test(koko.intKokoEatingBananasNeet);
    }
}

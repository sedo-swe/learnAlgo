package main.ltcode_gfg._11_graphs.maze;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;


@FunctionalInterface
interface IntMaze {
    String getPath(int[][] paths);
}

public class Maze {
    /*
        2nd idea
            --> using backtracking
            time O(n), space O(n)
     */
    IntMaze intMazeBacktrackingDFS = (paths -> {
        Set<String> visitedSet = new HashSet<>();
        StringBuffer answer = new StringBuffer();
        backtrackingDFS(paths, new int[]{0, 0}, answer, visitedSet);
        return answer.toString().trim();
    });

    private boolean backtrackingDFS(int[][] paths, int[] current, StringBuffer answer, Set<String> visitedSet) {
        // Return true when the current trampoline is Goal
        if (paths[current[0]][current[1]] == 0) {
            return true;
        }

        // Add current trampoline into visited set
        visitedSet.add(current[0] + "," + current[1]);

        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};  // S(D), E(R), N(U), W(L)
        String[] directionLetter = new String[]{"S", "E", "N", "W"};
        for (int i = 0; i < 4; i++) {
            // Calculate next trampoline
            int[] nextTo = new int[]{
                    current[0] + paths[current[0]][current[1]] * directions[i][0],
                    current[1] + paths[current[0]][current[1]] * directions[i][1]
            };

            // Check next trampoline is within maze
            if (nextTo[0] >= 0 && nextTo[1] >= 0 && nextTo[0] < paths.length && nextTo[1] < paths[0].length) {
                // Check next trampoline is visited (
                if (!visitedSet.contains(nextTo[0] + "," + nextTo[1])) {
                    int length = answer.length();
                    // Add direction for next trampoline
                    answer.append(directionLetter[i] + " ");
                    boolean isGoal = backtrackingDFS(paths, nextTo, answer, visitedSet);
                    if (isGoal) {
                        return true;
                    } else {
                        // Remove the direction added before
                        answer.setLength(length);
                    }
                }
            }
        }
        return false;
    }

    IntMaze intMazeSimpleDFS = (paths -> {
        Set<String> visitedSet = new HashSet<>();
        String answer = dfsHelperSimple(paths, new int[]{0, 0}, visitedSet);
        return answer.trim();
    });

    private String dfsHelperSimple(int[][] paths, int[] current, Set<String> visitedSet) {
        // Return true when the current trampoline is Goal
        if (paths[current[0]][current[1]] == 0) {
            return "";
        }

        // Add current trampoline into visited set
        visitedSet.add(current[0] + "," + current[1]);

        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};  // S(D), E(R), N(U), W(L)
        String[] directionLetter = new String[]{"S", "E", "N", "W"};
        for (int i = 0; i < 4; i++) {
            // Calculate next trampoline
            int[] nextTo = new int[]{
                    current[0] + paths[current[0]][current[1]] * directions[i][0],
                    current[1] + paths[current[0]][current[1]] * directions[i][1]
            };

            // Check next trampoline is within maze
            if (nextTo[0] >= 0 && nextTo[1] >= 0 && nextTo[0] < paths.length && nextTo[1] < paths[0].length) {
                // Check next trampoline is visited (
                if (!visitedSet.contains(nextTo[0] + "," + nextTo[1])) {
                    // Add direction for next trampoline
                    String temp = dfsHelperSimple(paths, nextTo, visitedSet);
                    if (!"no path".equals(temp)) {
                        return directionLetter[i] + " " + temp;
                    }
                }
            }
        }
        return "no path";
    }

    IntMaze intMazeBFS = (paths -> {
        int rowLength = paths.length, colLength = paths[0].length;
        Set<String> visitedSet = new HashSet<>();
        Map<String, int[]> parentMap = new HashMap();
        parentMap.put("0,0", null);
        Queue<int[]> trams = new ArrayDeque<>();
        trams.offer(new int[]{0, 0, 0});

        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};  // S(D), E(R), N(U), W(L)
        String[] directionLetter = new String[]{"S", "E", "N", "W"};

        while (!trams.isEmpty()) {
            int[] current = trams.poll();
            visitedSet.add(current[0]+","+current[1]);

            for (int i = 0; i < 4; i++) {
                // Calculate next trampoline
                int[] nextTo = new int[]{
                        current[0] + paths[current[0]][current[1]] * directions[i][0],
                        current[1] + paths[current[0]][current[1]] * directions[i][1],
                        i   // direction to next trampoline
                };

                // Check next trampoline is within maze
                if (nextTo[0] >= 0 && nextTo[1] >= 0 && nextTo[0] < rowLength && nextTo[1] < colLength) {
                    // Check next trampoline is visited (
                    if (!visitedSet.contains(nextTo[0] + "," + nextTo[1])) {
                        int[] parent = new int[]{current[0], current[1], nextTo[2]};
                        parentMap.put(nextTo[0] + "," + nextTo[1], parent);
                        // Add direction for next trampoline
                        if (paths[nextTo[0]][nextTo[1]] == 0) {
                            // Record path to the Goal while going back to start via parent info
                            StringBuffer ans = new StringBuffer();
                            int[] s = parentMap.get((rowLength - 1) + "," + (colLength - 1));
                            while (s != null) {
                                ans.insert(0, directionLetter[s[2]] + " ");
                                s = parentMap.get(s[0] + "," + s[1]);
                            }
                            return ans.toString();
                        }
                        trams.offer(nextTo);
                    }
                }
            }
        }

        return "no path";
    });

    public void test(IntMaze func) {
        int[][] paths1 = new int[][] {
                {2, 1, 1},
                {1, 2, 2},
                {1, 1, 0}
        };
        System.out.println("Expected: S E E, Actual: " + func.getPath(paths1));

        int[][] paths1_1 = new int[][] {
                {2, 1, 1},
                {1, 1, 1},
                {1, 2, 0}
        };
        System.out.println("Expected: E S S, Actual: " + func.getPath(paths1_1));

        int[][] paths2 = new int[][] {
                {2, 1, 1, 1},
                {1, 2, 2, 2},
                {1, 1, 2, 3},
                {1, 1, 2, 0}
        };
        System.out.println("Expected: E W S E S, Actual: " + func.getPath(paths2));

        int[][] paths3 = new int[][] {
            {3, 6, 4, 3, 2, 4, 3},
            {2, 1, 2, 3, 2, 5, 2},
            {2, 3, 4, 3, 4, 2, 3},
            {2, 4, 4, 3, 4, 2, 2},
            {4, 5, 1, 3, 2, 5, 4},
            {4, 3, 2, 2, 4, 5, 6},
            {2, 5, 2, 5, 6, 1, 0}
        };
        System.out.println("Expected: E S S N S W E N W E E W S E N W S E, Actual: " + func.getPath(paths3));
    }

    public static void main(String[] args) {
        Maze maze = new Maze();
//        maze.test(maze.intMazeBacktrackingDFS);
//        System.out.println("=======");
//        maze.test(maze.intMazeSimpleDFS);
        maze.test(maze.intMazeBFS);

        Map<int[], int[]> parents = new HashMap<>();
        parents.put(new int[]{1, 0}, new int[]{0, 0});
        System.out.println(parents.containsKey(new int[]{1, 0}));
        System.out.println(parents.get(new int[]{1, 0}));
    }
}



/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    new Main().bfsSolution();
  }

  private void bfsSolution() {
    // final int[][] maze = new int[][] {{2, 1, 1}, {1, 2, 2}, {1, 1, 0}};
    final int[][] maze = new int[][] {
      {3,6,4,3,2,4,3},
      {2,1,2,3,2,5,2},
      {2,3,4,3,4,2,3},
      {2,4,4,3,4,2,2},
      {4,5,1,3,2,5,4},
      {4,3,2,2,4,5,6},
      {2,5,2,5,6,1,0}
    };

    int rows = maze.length, cols = maze[0].length;

    int[] directionRow = new int[] {-1, 1, 0, 0};
    int[] directionCol = new int[] {0, 0, -1, 1};

    Integer[][][] parent = new Integer[rows][cols][2];
    for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) parent[r][c] = null;

    Deque<Integer[]> dq = new LinkedList<>();

    parent[0][0] = new Integer[] {-1, -1};
    dq.addLast(new Integer[] {0, 0});

    while (!dq.isEmpty()) {
      Integer[] cur = dq.pollFirst();
      int row = cur[0], col = cur[1];
      if (row == rows - 1 && col == cols - 1)
        break;
      int step = maze[row][col];

      for (int i = 0; i < 4; i++) {
        int nextRow = row + directionRow[i] * step;
        int nextCol = col + directionCol[i] * step;
        if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || parent[nextRow][nextCol] != null) continue;
        parent[nextRow][nextCol] = new Integer[] {row, col};
        dq.addLast(new Integer[] {nextRow, nextCol});
      }
    }

    int curRow = rows - 1, curCol = cols - 1;
    List<int[]> pathList = new ArrayList<>();
    while (curRow != -1 || curCol != -1) {
      pathList.add(0, new int[] {curRow, curCol});
      int preRow = parent[curRow][curCol][0];
      int preCol = parent[curRow][curCol][1];
      curRow = preRow;
      curCol = preCol;
    }
    System.out.println(pathList.stream().map(Arrays::toString).collect(Collectors.joining(",")));
  }
}

 */

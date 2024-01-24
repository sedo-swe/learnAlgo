package main.ltcode.medium;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 *  200. Number of Islands (Medium)
 */
@FunctionalInterface
interface IntNumberOfIslands {
    int numIslands(char[][] grid);
}
public class NumberOfIslands {
    IntNumberOfIslands intNumberOfIslandsDFS = (grid -> {
        int islands = 0;
        if (grid == null || grid.length == 0) {
            return islands;
        }
        int row = grid.length, col = grid[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    islands++;
                }
            }
        }
        return islands;
    });

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0
                || r >= grid.length || c >= grid[0].length
                || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    IntNumberOfIslands intNumberOfIslandsBFS = (grid -> {
        int islands = 0;
        if (grid == null || grid.length == 0) {
            return islands;
        }
        int rows = grid.length, cols = grid[0].length;
        Set<Point> visited = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited.contains(new Point(r, c))) {
                    bfs(grid, r, c, visited);
                    islands++;
                }
            }
        }
        return islands;
    });

    private void bfs(char[][] grid, int r, int c, Set<Point> visited) {
        Deque<Point> queue = new ArrayDeque<>();
        Point cell = new Point(r, c);
        visited.add(cell);
        queue.add(cell);

        while (!queue.isEmpty()) {
            Point cur = queue.removeFirst();
            int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            for (int[] direction : directions) {
                Point temp = new Point(cur.x + direction[0], cur.y + direction[1]);
                if (0 <= temp.x && 0 <= temp.y
                        && temp.x < grid.length && temp.y < grid[0].length
                        && grid[temp.x][temp.y] == '1'
                        && !visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }
        }

    }



    public void test(IntNumberOfIslands func) {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Expected: 1, Actual: " + func.numIslands(grid1));

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Expected: 3, Actual: " + func.numIslands(grid2));
    }

    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        System.out.println("= DFS =======");
        numberOfIslands.test(numberOfIslands.intNumberOfIslandsDFS);
        System.out.println("= BFS =======");
        numberOfIslands.test(numberOfIslands.intNumberOfIslandsBFS);
        Point p1 = new Point(1, 1);
        Set<Point> points = new HashSet<>();
        points.add(p1);
        Point p2 = new Point(1, 1);
        System.out.println("Contains? " + points.contains(p2));
        p2.x = p2.x + 1;
        System.out.println("Contains? " + points.contains(p2));

    }
}

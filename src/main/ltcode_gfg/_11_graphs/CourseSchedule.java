package main.ltcode_gfg._11_graphs;

import java.util.*;

/**
 *  207. Course Schedule (Medium)
 */
@FunctionalInterface
interface IntCourseSchedule {
    boolean canFinish(int numCourses, int[][] prerequisites);
}

public class CourseSchedule {

    IntCourseSchedule intCourseSchedule = (((numCourses, prerequisites) -> {
        // prerequisites : [[0, 1], [0, 2]]
        Map<Integer, List<Integer>> depenciesMap = new HashMap<>(); // prerequisite : list of courses
        int[] numOfPrerequisitesPerCourse = new int[numCourses];
        for (int[] preq : prerequisites) {
            int course = preq[0];
            int prerequisite = preq[1];
            depenciesMap.computeIfAbsent(prerequisite, ignored -> new ArrayList<>()).add(course);
            numOfPrerequisitesPerCourse[course]++;
        }

        // Find courses having no prerequisites
        // numOfPrerequisitesPerCourse : [2, 0, 0]
        int courseFinished = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numOfPrerequisitesPerCourse.length; i++) {
            if (numOfPrerequisitesPerCourse[i] == 0) {
                deque.offer(i);
                courseFinished++;
            }
        }

        while (!deque.isEmpty()) {
            int cur = deque.poll();

            for (int next : depenciesMap.getOrDefault(cur, new ArrayList<>())) {
                numOfPrerequisitesPerCourse[next]--;
                if (numOfPrerequisitesPerCourse[next] == 0) {
                    deque.add(next);
                    courseFinished++;
                }
            }
        }
        return courseFinished == numCourses;
    }));

    // [[1,0], [3,2]]
    Map<Integer, List<Integer>> prereqPerCourse = new HashMap<>();
    IntCourseSchedule intCourseScheduleDFS = (((numCourses, prerequisites) -> {
        // map each course to prerequisites list
        for (int[] preq : prerequisites) {
            int course = preq[0];
            int prerequisite = preq[1];
            prereqPerCourse.computeIfAbsent(course, ignored -> new ArrayList<>()).add(prerequisite);
        }

        // visitSet = all courses along the current DFS path, will remove when we leave it
        Set<Integer> visitSet = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, visitSet)) {
                return false;
            }
        }
        return true;
    }));

    public boolean dfs(int course, Set<Integer> visitedSet) {
        // Base case
        if (visitedSet.contains(course)) {
            return false;
        }
        if (prereqPerCourse.getOrDefault(course, new ArrayList<>()).size() == 0) {
            return true;
        }

        // Recursive case
        visitedSet.add(course);
        for (int next : prereqPerCourse.getOrDefault(course, new ArrayList<>())) {
            if (!dfs(next, visitedSet)) {
                return false;
            }
        }
        visitedSet.remove(course);
        prereqPerCourse.put(course, new ArrayList<>());
        return true;
    }

    IntCourseSchedule intCourseScheduleSol = ((numCourses, prerequisites) -> {
        if (numCourses == 0) {
            return true;
        }

        int[] numOfCoursePerPrerequisite = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            numOfCoursePerPrerequisite[prerequisites[i][1]]++;
        }

        boolean[] visited = new boolean[prerequisites.length];
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < prerequisites.length; i++) {
                if (!visited[i]) {
                    if (numOfCoursePerPrerequisite[prerequisites[i][0]] == 0) {
                        visited[i] = true;
                        numOfCoursePerPrerequisite[prerequisites[i][1]]--;
                        flag = true;
                    }
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (numOfCoursePerPrerequisite[i] != 0) {
                return false;
            }
        }
        return true;
    });


    public void test(IntCourseSchedule func) {
        System.out.println("Expected: true, Actual: " + func.canFinish(2, new int[][] {{1,0}}));
        System.out.println("Expected: false, Actual: " + func.canFinish(2, new int[][] {{1,0}, {0,1}}));
        System.out.println("Expected: true, Actual: " + func.canFinish(4, new int[][] {{1,0}, {3,2}}));
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        cs.test(cs.intCourseSchedule);
        System.out.println();
        cs.test(cs.intCourseScheduleDFS);
    }
}

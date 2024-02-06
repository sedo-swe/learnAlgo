package main.ltcode.problems_seen;

import java.util.*;

public class KeywordSearch {
    /*
    O(m * n * log(n))
    The code has a nested loop where the outer loop iterates over the jobDescriptions list, which has 'm' elements, and the inner loop iterates over the keywords list, which has 'n' elements. Inside the inner loop, the code checks if each keyword is present in the current job description using the contains() method, which has a time complexity of O(k), where 'k' is the length of the keyword. Therefore, the overall time complexity is O(m * n * k). Additionally, the code uses a priority queue to sort the job descriptions based on the keyword count, which has a time complexity of O(n * log(n)), where 'n' is the number of job descriptions. Hence, the final time complexity is O(m * n * k + n * log(n)).
    */
    public static List<String> topJobDescriptions(List<String> jobDescriptions, List<String> keywords) {
        Map<String, Integer> keywordCount = new HashMap<>();

        // Count the number of keywords in each job description
        for (String jobDescription : jobDescriptions) {
            int count = 0;
            for (String keyword : keywords) {
                if (jobDescription.contains(keyword)) {
                    count++;
                }
            }
            keywordCount.put(jobDescription, count);
        }

        // Use PriorityQueue to maintain the top 5 job descriptions based on keyword count
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue()));

        pq.addAll(keywordCount.entrySet());

        // Retrieve the top 5 job descriptions
        List<String> result = new ArrayList<>();
        int count = 0;
        while (count < 5 && !pq.isEmpty()) {
            Map.Entry<String, Integer> t = pq.poll();
            result.add(t.getKey() + " === " + t.getValue());
            count++;
        }

        return result;
    }

    /* Improved version
    O(n^2)
    The main loop iterates through each job description, resulting in a time complexity of O(n), where n is the number of job descriptions. Within each iteration, the getKeywordCount method is called, which iterates through each keyword in the list of keywords. In the worst case, the job description contains all the keywords, resulting in another loop of O(m), where m is the number of keywords. Therefore, the overall time complexity is O(n * m), which can be simplified to O(n^2) if we consider m to be a constant.
     */
    public static List<String> topJobDescriptionsImproved(List<String> jobDescriptions, List<String> keywords) {
        // Create a min heap with a custom comparator to compare job descriptions based on keyword count
        PriorityQueue<String> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(jobDescription -> getKeywordCount(jobDescription, keywords)));

        // Iterate through each job description
        for (String jobDescription : jobDescriptions) {
            minHeap.offer(jobDescription);

            // If the size exceeds 5, remove the job description with the lowest keyword count
            if (minHeap.size() > 5) {
                minHeap.poll();
            }
        }

        // Convert the min heap to a list
        List<String> result = new ArrayList<>(minHeap);

        // Reverse the list to get the top job descriptions in descending order of keyword count
        Collections.reverse(result);

        return result;
    }

    private static int getKeywordCount(String jobDescription, List<String> keywords) {
        int count = 0;
        for (String keyword : keywords) {
            if (jobDescription.contains(keyword)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<String> jobDescriptions = Arrays.asList(
                "Software Engineer position: Java, Python, C++",
                "Data Scientist role: Machine Learning, Python, Statistics",
                "Web Developer opening: HTML, CSS, JavaScript",
                "Database Administrator job: SQL, Oracle, MongoDB",
                "Network Engineer role: Cisco, Networking, Security",
                "Java Developer position with experience in Spring and Hibernate.",
                "Senior Software Engineer needed for Python and Django projects.",
                "Full Stack Developer role, proficient in JavaScript and React.",
                "Backend Engineer position with expertise in Java and Spring Boot."
        );

//        List<String> keywords = Arrays.asList("Java", "Python", "SQL", "Machine Learning");
        List<String> keywords = Arrays.asList("Java", "Software", "Engineer", "Spring");

        List<String> topJobDescriptions = topJobDescriptions(jobDescriptions, keywords);

        System.out.println("Top 5 Job Descriptions:");
        for (String description : topJobDescriptions) {
            System.out.println(description);
        }
        System.out.println("\n");

        List<String> topJobDescriptions2 = topJobDescriptionsImproved(jobDescriptions, keywords);

        System.out.println("Top 5 Job Descriptions:");
        for (String description : topJobDescriptions2) {
            System.out.println(description);
        }
    }
}

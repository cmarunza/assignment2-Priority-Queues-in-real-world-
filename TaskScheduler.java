import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

class Job implements Comparable<Job> {
    int id;
    int processingTime;

    public Job(int id, int processingTime) {
        this.id = id;
        this.processingTime = processingTime;
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(this.processingTime, other.processingTime);
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        PriorityQueue<Job> jobQueue = new PriorityQueue<>();
        try {
            Scanner scanner = new Scanner(new File("./inputs/task1-input.txt"));
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                int processingTime = scanner.nextInt();
                jobQueue.add(new Job(id, processingTime));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            return;
        }

        int currentTime = 0;
        int totalCompletionTime = 0;
        StringBuilder executionOrder = new StringBuilder();

        while (!jobQueue.isEmpty()) {
            Job job = jobQueue.poll();
            currentTime += job.processingTime;
            totalCompletionTime += currentTime;
            executionOrder.append(job.id).append(", ");
        }

        double averageCompletionTime = (double) totalCompletionTime / 100;
        System.out.println("Execution order: [" + executionOrder.toString().replaceAll(", $", "") + "]");
        System.out.println("Average completion time: " + averageCompletionTime);
    }
}

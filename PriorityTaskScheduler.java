import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class JobWithPriority implements Comparable<JobWithPriority> {
    int id;
    int processingTime;
    int priority;

    public JobWithPriority(int id, int processingTime, int priority) {
        this.id = id;
        this.processingTime = processingTime;
        this.priority = priority;
    }

    @Override
    public int compareTo(JobWithPriority other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }
        return Integer.compare(this.processingTime, other.processingTime);
    }
}

public class PriorityTaskScheduler {
    public static void main(String[] args) {
        PriorityQueue<JobWithPriority> jobQueue = new PriorityQueue<>();
        try {
            Scanner scanner = new Scanner(new File("./inputs/task2-input.txt"));
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                int processingTime = scanner.nextInt();
                int priority = scanner.nextInt();
                jobQueue.add(new JobWithPriority(id, processingTime, priority));
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
            JobWithPriority job = jobQueue.poll();
            currentTime += job.processingTime;
            totalCompletionTime += currentTime;
            executionOrder.append(job.id).append(", ");
        }

        double averageCompletionTime = (double) totalCompletionTime / 100;
        System.out.println("Execution order: [" + executionOrder.toString().replaceAll(", $", "") + "]");
        System.out.println("Average completion time: " + averageCompletionTime);
    }
}

import java.util.*;

public class MultipleServerScheduling {

    // Job class representing each job with id, profit, and deadline
    static class Job {
        int id;
        int profit;
        int deadline;

        public Job(int id, int profit, int deadline){
            this.id = id;
            this.profit = profit;
            this.deadline = deadline;
        }
    }

    /**
     * Schedules jobs based on the provided algorithm.
     * @param jobs List of jobs sorted in non-increasing order of profit.
     * @param n Number of jobs.
     * @return List of job IDs representing the optimal sequence.
     */
    public static List<Integer> scheduleJobs(List<Job> jobs, int n){
        // Sort jobs in non-increasing order of profit
        jobs.sort((a, b) -> b.profit - a.profit);

        List<Integer> J = new ArrayList<>();
        if(n >=1){
            J.add(jobs.get(0).id); // Initialize J with first job
        }

        int p = 1; // Current number of jobs in J

        for(int i =1; i <n; i++){
            List<Integer> K = new ArrayList<>(J); // Copy current schedule

            // Insert job i into K maintaining non-decreasing order of deadlines
            int m = p -1;
            while(m >=0 && jobs.get(i).deadline < getDeadline(jobs, K.get(m))){
                m--;
            }
            K.add(m+1, jobs.get(i).id); // Insert at the correct position
            p++;

            if(isFeasible(K, p, jobs)){
                J = K; // Accept the new schedule
            }
            else{
                p--; // Reject the new job
            }
        }

        return J;
    }

    /**
     * Checks if the current schedule is feasible.
     * @param K Current schedule.
     * @param p Number of jobs in the current schedule.
     * @param jobs List of all jobs.
     * @return True if feasible, else False.
     */
    public static boolean isFeasible(List<Integer> K, int p, List<Job> jobs){
        for(int i =0; i <p; i++){
            int jobId = K.get(i);
            int jobDeadline = getDeadline(jobs, jobId);
            if(jobDeadline < (i+1)){
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the deadline of a job based on its ID.
     * @param jobs List of all jobs.
     * @param jobId ID of the job.
     * @return Deadline of the job.
     */
    public static int getDeadline(List<Job> jobs, int jobId){
        for(Job job : jobs){
            if(job.id == jobId){
                return job.deadline;
            }
        }
        return -1; // Not found
    }

    /**
     * Prints the optimal job schedule.
     * @param schedule List of job IDs.
     */
    public static void printSchedule(List<Integer> schedule){
        System.out.println("\nOptimal Job Sequence:");
        for(int jobId : schedule){
            System.out.print("J" + jobId + " ");
        }
        System.out.println();
    }

    /**
     * Calculates the total profit of the scheduled jobs.
     * @param schedule List of job IDs.
     * @param jobs List of all jobs.
     * @return Total profit.
     */
    public static int calculateTotalProfit(List<Integer> schedule, List<Job> jobs){
        int total =0;
        for(int jobId : schedule){
            for(Job job : jobs){
                if(job.id == jobId){
                    total += job.profit;
                    break;
                }
            }
        }
        return total;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Multiple Server Scheduling Problem");
        System.out.println("-----------------------------------\n");

        System.out.print("Enter the number of jobs (n): ");
        int n = scanner.nextInt();

        List<Job> jobs = new ArrayList<>();
        System.out.println("Enter the details of each job in the format: id profit deadline");
        for(int i =0; i <n; i++){
            System.out.print("Job " + (i+1) + ": ");
            int id = scanner.nextInt();
            int profit = scanner.nextInt();
            int deadline = scanner.nextInt();
            jobs.add(new Job(id, profit, deadline));
        }

        // Schedule the jobs
        List<Integer> optimalSchedule = scheduleJobs(jobs, n);

        // Print the optimal schedule
        printSchedule(optimalSchedule);

        // Calculate and print total profit
        int totalProfit = calculateTotalProfit(optimalSchedule, jobs);
        System.out.println("Total Profit: " + totalProfit);

        scanner.close();
    }
}
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class ForkJoinDemo {
    public static void main(String[] args) {
        // Initialize the forkjoinpool
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // Create the data which requires some computation
        double[] nums = new double[1_00_000];
        for (int i = 0; i < nums.length; i++)
            nums[i] = (double) i;

        // Print first 10 records from the composed data
        System.out.println("A portion of original sequence");
        for (int i = 0; i < 10; i++)
            System.out.print(nums[i] + " ");
        System.out.println();

        // Now start the task by calling invoke from the forkJoinPool and passing the
        // data to the task
        forkJoinPool.invoke(new SqrtTransaform(nums, 0, nums.length));

        // By the time the code reaches here, all the comutations would be finish
        // Now just print what you got, first few of them
        for (int i = 0; i < 10; i++)
            System.out.format("%.4f ", nums[i]);

        System.out.println();

        // This is optional, since it would happen automatically, according to theory
        forkJoinPool.shutdown();

    }
}

class SqrtTransaform extends RecursiveAction {

    // Set a threshold, so that the bulk task can be divided into small chunk of
    // these many tasks
    final int seqThreshold = 1000;

    // Start and end for divide and conquer technique
    int start, end;
    double[] data;

    // Constructor
    SqrtTransaform(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    // Implementation of the Recursive Action
    protected void compute() {

        // This is the actual computation step
        if ((end - start) < 1000) {
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        }
        // This is where we divide the task into more subtasks
        // Until it reaches the threshold
        else {

            int middle = (start + end) / 2;

            invokeAll(new SqrtTransaform(data, start, middle));
            invokeAll(new SqrtTransaform(data, middle + 1, end));
        }
    }
}
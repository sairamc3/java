import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class RecursiveTaskDemo {
    public static void main(String[] args) {

        // ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // Composing the data
        double[] nums = new double[5000];
        // Assign positive numbers for even index
        // Negative numbers for odd index
        for (int i = 0; i < nums.length; i++)
            nums[i] = (double)((i % 2) == 0 ? i : -i);
        
        Sum sum = new Sum(nums, 0, nums.length);

        double summation = forkJoinPool.invoke(sum);

        System.out.println("Summation: " + summation);
    }
}

class Sum extends RecursiveTask<Double> {

    static int threshold = 500;

    int start, end;
    double[] data;

    Sum(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    protected Double compute() {

        double sum = 0;

        if ((end - start) < threshold) {
            for (int i = start; i < end; i++)
                sum += data[i];
        } else {

            int middle = (start + end) / 2;

            Sum subTaskA = new Sum(data, start, middle);
            Sum subTaskB = new Sum(data, middle + 1, end);

            subTaskA.fork();
            subTaskB.fork();

            sum = subTaskA.join() + subTaskB.join();
        }

        return sum;
    }
}
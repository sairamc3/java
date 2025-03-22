import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class FJExperiment{
    public static void main(String[] args) {
        int pLevel;
        int threshold;

        if(args.length != 2){
            System.out.println("Usage: FJExperiment Parllelism Threshold ");
            return;
        }

        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        // These variables used to time the task
        long beginTime, endTime;

        // Create a pool with pLevel - Parllelism
        ForkJoinPool forkJoinPool = new ForkJoinPool(pLevel);

        // Composing the required data
        double[] nums = new double[1_00_000];
        for(int i=0; i< nums.length; i++) nums[i] = (double) i;

        // Start time
        beginTime = System.nanoTime();

        Transform task = new Transform(nums, 0, nums.length, threshold);

        forkJoinPool.invoke(task);

        endTime = System.nanoTime();

        System.out.println("Level of Parllelism: " + pLevel);
        System.out.println("Sequential threshold: " + threshold);
        System.out.println("Elapsed time: " + (endTime - beginTime)+ " ns");
        System.out.println();

    }
}

class Transform extends RecursiveAction {

    int threshold, start, end;
    double[] data;

    Transform(double[] data, int start, int end, int threshold){
        this.data = data;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    protected void compute(){

        if((end - start) < this.threshold){
            for(int i=start; i<end; i++){
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            int middle = (start + end)/2;

            invokeAll(new Transform(data, start, middle, threshold),
            new Transform(data, middle+1, end, threshold));
        }
    }
}
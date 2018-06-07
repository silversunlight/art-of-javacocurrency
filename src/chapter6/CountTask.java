package chapter6;

import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 计算1+2+3+4
 * 使用Fork/Join 拆分合并任务
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;//阈值
    private int start;
    private int end;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        //生成一个计算任务，计算1+2+3+4
        CountTask task = new CountTask(1, 4);
        //执行一个任务
        Future<Integer> integerFuture = forkJoinPool.submit(task);
        try {
            System.out.println(integerFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum=0;
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; ++i) {
                sum += i;
            }
        } else {
            //如果任务大于阈值，就分裂成两个子任务计算
            int middle=(start+end)/2;
            CountTask leftTsak = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //执行子任务
            leftTsak.fork();
            rightTask.fork();
            //等待子任务完成，并得到其结果
            int leftResult=leftTsak.join();
            int rightResult=rightTask.join();
            //合并子任务
            sum=leftResult+rightResult;
        }
        return sum;
    }
}

package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV4 {
    //static final int TASK_SIZE = 1100; //1. 일반
    static final int TASK_SIZE = 1200; //2. 긴급
    //static final int TASK_SIZE = 1201; //2. 거절
    public static void main(String[] args) {

        //LinkedBlockingQueue 의 기본 사이즈(Integer.MAX_VALUE)로는 추가스레드가 사용될 수 없다.
        //큐의 사이즈가 가득차야 추가 스레드가 생성되고 사용된다.
        int queueSize = 1000;
        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                //초기 스레드 100개, 최대 스레드 200개(초과시 100개 투입), 추가 스레드 생존시간, 생존시간단위, 대기큐(사이즈)
                100, 200, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize)
        );

        long startMs = System.currentTimeMillis();

        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                ex.execute(new RunnableTask(taskName));
                printState(ex, taskName);
            }catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        ex.close();
        long endMs = System.currentTimeMillis();
        log("time : " + (endMs - startMs));
    }
}

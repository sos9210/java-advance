package thread.executor.poolsize;

import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue  = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);

        printState(ex);
        ex.execute(new RunnableTask("task1"));
        printState(ex);
        ex.execute(new RunnableTask("task2"));
        printState(ex);
        ex.execute(new RunnableTask("task3"));
        printState(ex);
        ex.execute(new RunnableTask("task4"));
        printState(ex);
        ex.execute(new RunnableTask("task5"));
        printState(ex);
        ex.execute(new RunnableTask("task6"));
        printState(ex);
        try {
            ex.execute(new RunnableTask("task7"));
        } catch (RejectedExecutionException e) {
            log("task7 실행거절 예외발생 : " + e);
        }

        sleep(3000);
        log("== 작업수행완료 ==");
        printState(ex);

        sleep(3000);

        log("== MaximumPoolSize 대기시간초과 =="); // pool 4 -> 2
        printState(ex);

        ex.close();
        log("== shutdown 완료 ==");
        printState(ex);

    }
}

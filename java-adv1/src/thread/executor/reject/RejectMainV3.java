package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {
    public static void main(String[] args) {
        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy() //CallerRunsPolicy: 작업을 전달한 스레드에게 작업 위임함
        );

        ex.submit(new RunnableTask("task1"));
        ex.submit(new RunnableTask("task2"));
        ex.submit(new RunnableTask("task3"));
        ex.submit(new RunnableTask("task4"));

        ex.close();

    }
}

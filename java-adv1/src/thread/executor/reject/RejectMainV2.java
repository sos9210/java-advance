package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class RejectMainV2 {
    public static void main(String[] args) {
        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.DiscardPolicy() //DiscardPolicy: 초과된 작업은 그냥 무시한다(버림)
        );

        ex.submit(new RunnableTask("task1"));
        ex.submit(new RunnableTask("task2"));
        ex.submit(new RunnableTask("task3"));
        ex.submit(new RunnableTask("task4"));
        ex.close();

    }
}

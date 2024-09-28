package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class RejectMainV1 {
    public static void main(String[] args) {
        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.AbortPolicy() //거절할때 정책 기본정책은 AbortPolicy(), RejectedExecutionException 발생시킴
        );

        ex.submit(new RunnableTask("task1"));
        try {
            ex.submit(new RunnableTask("task2"));
        }catch (RejectedExecutionException e) {
            log("요청초과");
            //포기 다시시도 등을 고민하면된다.
            log(e);
        }
        ex.close();

    }
}

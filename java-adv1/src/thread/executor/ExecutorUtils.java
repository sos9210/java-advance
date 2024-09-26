package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {
    public static void printState(ExecutorService executorService) {
        
        //executorService instanceof ThreadPoolExecutor poolExecutor
        //instanceof에서 ThreadPoolExecutor poolExecutor 
        //ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor)executorService;
        //이것과 같음
        if(executorService instanceof ThreadPoolExecutor poolExecutor) {
            int active = poolExecutor.getActiveCount();                 //생성된 스레드 개수
            int pool = poolExecutor.getPoolSize();                      //작업중인 스레드 개수
            int queuedTasks = poolExecutor.getQueue().size();           //큐 사이즈
            long completedTask = poolExecutor.getCompletedTaskCount();  //완료된 작업수
            log("[pool = " + pool + ", active = " + active + ", queued = "+queuedTasks+
                    ", completedTask = " + completedTask + "]");

        } else {
            log(executorService);

        }
    }
    // 추가
    public static void printState(ExecutorService executorService, String taskName) {
        if(executorService instanceof ThreadPoolExecutor poolExecutor) {
            int active = poolExecutor.getActiveCount();                 //생성된 스레드 개수
            int pool = poolExecutor.getPoolSize();                      //작업중인 스레드 개수
            int queuedTasks = poolExecutor.getQueue().size();           //큐 사이즈
            long completedTask = poolExecutor.getCompletedTaskCount();  //완료된 작업수
            log(taskName + " -> [pool = " + pool + ", active = " + active + ", queued = "+queuedTasks+
                    ", completedTask = " + completedTask + "]");
        } else {
            log(executorService);

        }
    }
}

package thread.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        log("== 초기 상태 ==");
        printState(threadPoolExecutor);
        //[pool = 0, active = 0, queued = 0, completedTask = 0]

        threadPoolExecutor.execute(new RunnableTask("taskA"));
        threadPoolExecutor.execute(new RunnableTask("taskB"));
        threadPoolExecutor.execute(new RunnableTask("taskC"));
        threadPoolExecutor.execute(new RunnableTask("taskD"));

        log("== 작업 수행 중 ==");
        printState(threadPoolExecutor);
        //[pool = 2, active = 2, queued = 2, completedTask = 0]

        sleep(3000);
        log("== 작업수행완료 ==");
        printState(threadPoolExecutor);
        //[pool = 2, active = 0, queued = 0, completedTask = 4]

        threadPoolExecutor.close();
        log("== shutdown 완료 ==");
        printState(threadPoolExecutor);
        //[pool = 0, active = 0, queued = 0, completedTask = 4]
    }
}

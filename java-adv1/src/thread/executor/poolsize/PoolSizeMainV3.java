package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV3 {

    public static void main(String[] args) {
        // 초기스레드 개수 0
        // 기본적으로 스레드가 60초간 생존
        // 최대 스레드는 제한없음
        // 생산자스레드->소비자스레드 작업요청 바로전달 (대기큐에 쌓이지않음)
        ExecutorService ex = Executors.newCachedThreadPool();

        log("pool 생성");
        printState(ex);

        for (int i = 1; i <= 100; i++) {
            String taskName = "Task" + i;
            ex.execute(new RunnableTask(taskName));
            printState(ex, taskName);
        }
        sleep(60000);
        log("작업수행완료");
        printState(ex);

        sleep(60000);
        log("MaximumPoolSize 초과");
        printState(ex);


        ex.close();
        log("== shutdown 완료 ==");
    }
}

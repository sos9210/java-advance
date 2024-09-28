package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        // 고정 스레드풀 전략
        // 고정된 개수의 스레드만 생성하고 대기큐의 사이즈 제한은 없음
        // cpu,메모리 리소스 예측이 가능한 안정적인 방식
        ExecutorService ex = Executors.newFixedThreadPool(2);

        log("pool 생성");
        printState(ex);

        for (int i = 1; i <= 6; i++) {
            String taskName = "Task" + i;
            ex.execute(new RunnableTask(taskName));
            printState(ex, taskName);
        }
        ex.close();


    }
}

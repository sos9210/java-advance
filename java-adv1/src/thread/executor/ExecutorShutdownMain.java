package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.*;
import static util.MyLogger.log;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.execute(new RunnableTask("taskA"));
        ex.execute(new RunnableTask("taskB"));
        ex.execute(new RunnableTask("taskC"));
        ex.execute(new RunnableTask("longTask", 100_000));//100초 대기

        printState(ex);
        log("== shutdown 시작");
        shutdownAndAwaitTermination(ex);
        log("== shutdown 완료");
        printState(ex);
    }

    private static void shutdownAndAwaitTermination(ExecutorService ex) {
        ex.shutdown(); // non-blocking, 새로운 작업을 받지않음. 처리중이거나 큐에 이미 대기중인 작업은 처리하고 이후 풀의 스레드를 정리한다.

        try {
            //이미 대기중인 작업들을 모두 완료할때까지 10초 기다린다.

            boolean result = ex.awaitTermination(10, TimeUnit.SECONDS);
            if (!result) {
                //정상 종료가 너무 오래걸리면..
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                ex.shutdownNow();

                //작업이 취소될때까지 대기한다.
                if(!ex.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination()으로 대기중인 현재 스레드가 인터럽트 될 수 있다.
            ex.shutdownNow();
        }
    }
}

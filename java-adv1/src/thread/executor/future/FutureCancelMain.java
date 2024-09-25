package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {
    private static boolean mayInterruptIfRunning = false; //변경

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(1);
        Future<String> future = ex.submit(new MyTask());
        log("Future.state : " + future.state());

        //일정시간 후 취소시도
        sleep(3000);
        //cancel() 호출
        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("future.cancel(" + mayInterruptIfRunning + ") result : " + cancelResult);

        //결과
        try {
            log("Future result : " + future.get());
        } catch (CancellationException e) {
            log("Future는 이미 취소 되었습니다.");
        } catch (InterruptedException |ExecutionException e) {
            throw new RuntimeException(e);
        }
        ex.close();
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업중: " + i);
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e) {
                log("인터럽트 발생");
                return "Interrupted";
            }

            return "Completed";
        }
    }

}

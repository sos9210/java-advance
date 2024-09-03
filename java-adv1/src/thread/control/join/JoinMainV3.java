package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {
    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // 대상 스레드가 종료될때까지 대기 (TERMINATED가 될때까지 대기한다.)
        log("join() - main 스레드가 thread1, thread2 종료까지 대기한다.(WAITING)");
        thread1.join();
        thread2.join();
        log("main 스레드 대기 완료 (RUNNABLE)");

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        //thread1 , thread2는 계산이 끝나지 않았기 때문에 엉뚱한 값이 나온다.
        int sumAll = task1.result + task2.result;
        log("task1 + task2 = " + sumAll);

        log("End");
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;

        }

        @Override
        public void run() {
            log("작업시작");
            sleep(2000);

            int sum = 0;
            for (int i = startValue; i <= endValue ; i++) {
                sum += i;
            }

            result = sum;
            log("작업완료 result = " + result);
        }
    }
}

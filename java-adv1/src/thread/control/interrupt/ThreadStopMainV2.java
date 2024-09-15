package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    //V2 스레드가 sleep중 급하게 깨우거나 작업종료를 지시하는 방법
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task,"work");
        thread.start();

        sleep(4000);
        log("작업중단지시 thread.interrupt()");
        thread.interrupt(); // 해당 스레드가 sleep에서 깨어나고 InterruptedException 예외 발생
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted()); // true
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {

                while (true) { //while문 조건식에서는 인터럽트가 발생되지않음
                    log("작업중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); //false
                log("interrupt message=" + e.getMessage());
                log("state=" + Thread.currentThread().getState());
            }
            log("지원 정리");
            log("지원 종료");
        }
    }
}

package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    //V4 한번 인터럽트 상태를 확인한 후 다시 정상(false)로 바꿔 놓는 방법
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task,"work");
        thread.start();

        sleep(100);
        log("작업중단지시 thread.interrupt()");
        thread.interrupt(); // 해당 스레드가 sleep에서 깨어나고 InterruptedException 예외 발생
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted()); // true
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) { //인터럽트 상태를 변경함 true -> false
                log("작업중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); //true
            
            try {
                log("자원정리시도");
                Thread.sleep(1000); //스레드가 계속 인터럽트상태(isInterrupted() == true)이기 때문에 인터럽트 예외가 발생한다.
                log("자원정리완료");
            } catch (InterruptedException e) {
                log("자원정리실패 - 자원정리중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());   //false
            }
            
            log("작업종료");
        }
    }
}

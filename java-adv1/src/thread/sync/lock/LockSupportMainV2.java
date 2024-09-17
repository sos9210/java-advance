package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV2 {
    /**
     * 기존 synchronized 키워드를 사용하는 방법은 스레드를 block시킨다.
     * 하지만 무한정 대기라는 단점이 존재하기때문에 빠져나올 방법이 존재하지않는다.
     * LockSupport를 사용하여 이 단점을 해결해본다.
    **/
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        // 잠시 대기하여 Thread-1이 park 상태에 빠질시간을 준다.
        sleep(100);
        log("Thread-1 state : " + thread1.getState());

        log("main -> unpark(Thread-1)");
    }

    static class ParkTest implements Runnable {
        @Override
        public void run() {
            log("park 시작");
            LockSupport.parkNanos(2000_000000); //parkNanos 사용.. 2초
            log("park 종료, state : " + Thread.currentThread().isInterrupted());
            log("인터럽트 상태 : " + Thread.currentThread().getState());

        }
    }
}

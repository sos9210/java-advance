package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {
    //V1 특정 스레드의 작업을 중간에 중단하려면?
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task,"work");
        thread.start();

        sleep(4000);
        log("작업중단지시 runFlag=false");
        //작업중단지시를 내려도 쓰레드 sleep() 메서드에서 3초 대기 후 while조건을 확인하기 때문에 바로 중단되지 않는다.
        task.runFlag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업중");
                sleep(3000);
                
            }
            log("지원 정리");
            log("지원 종료");
        }
    }
}

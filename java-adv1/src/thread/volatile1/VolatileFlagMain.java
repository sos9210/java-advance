package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        log("runFlag = " + myTask.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경시도");
        myTask.runFlag = false;
        log("runFlag = " + myTask.runFlag);
        log("main 종료");
    }
    //메모리가시성 문제로 발생한 문제
    //문제점1. main스레드에서 변경한 runFlag값이 메인메모리 즉각적으로 반영되지않음.
    //문제점2. 1차메모리(RAM)에 runFlag값이 반영된다 하더라도 work스레드를 처리하는 cpu코어 또한 캐시메모리에서 runFlag값을 읽어오는 문제가있다.
    //문제점3. 위 문제점들은 언제 갱신될지 시점을 알 수 없다. 보통 컨텍스트 스위칭이 일어나는 시점에 갱신될 수 있다.

    //해결방법. volatile키워드를 사용하여 캐시메모리를 무시하고 1차메모리(RAM)에서 바로 값을 쓰고 읽어온다. 다만 성능상 이슈가 발생할 수 있다.
    static class MyTask implements Runnable {
        //boolean runFlag = true;
        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {   //main스레드에서 runFlag를 false로 변경했지만 빠져나오지 못함.
                // runFlag가 false로 변하면 탈출
            }
            log("task 종료");
        }
    }
}

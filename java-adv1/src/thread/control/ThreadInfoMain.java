package thread.control;

import thread.start.*;

import static util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {
        //main스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);  //Thread[스레드ID,스레드명,스레드우선순위,스레드그룹명]
        log("mainThread.threadId() = " + mainThread.threadId());        //스레드ID : (고유식별자,직접지정불가)
        log("mainThread.getName() = " + mainThread.getName());          //스레드명 : (중복가능)
        log("mainThread.getPriority() = " + mainThread.getPriority());  //스레드우선순위 : (5 기본, 10 가장높음, 1 가장낮음)
        log("mainThread.getState() = " + mainThread.getState());        //스레드상태 :
                                                                            //NEW 생성만되고 시작되지않음
                                                                            //RUNNABLE 스레드가 실행준비 혹은 실행중
                                                                            //BLOCKED 스레드가 동기화 락을 기다리는중
                                                                            //WAITING 스레드가 다른 스레드의 작업이 끝나기를 기다리는중
                                                                            //TIMED_WAITING 일정 시간동안 기다리는중
                                                                            //TERMINATED 스레드기 실행을 마침
        //main스레드
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("myThread = " + myThread);
        log("myThread.threadId() = " + myThread.threadId());
        log("myThread.getName() = " + myThread.getName());
        log("myThread.getPriority() = " + myThread.getPriority());
        log("myThread.getState() = " + myThread.getState());
    }
}

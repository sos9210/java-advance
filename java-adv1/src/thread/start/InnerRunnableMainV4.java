package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV4 {
    //익명클래스를 사용해 Runnable생성
    public static void main(String[] args) {
        log("main() start");

        Thread thread = new Thread(() -> log("run()"));
        thread.start();

        log("main() end");
    }
}

package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV2 {
    //익명클래스를 사용해 Runnable생성
    public static void main(String[] args) {
        log("main() start");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log("run()");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end");
    }
}

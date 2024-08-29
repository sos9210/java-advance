package thread.control;

import util.ThreadUtils;

public class CheckedExceptionMain {
    public static void main(String[] args) {

    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run()/*throws Exception */ {
            //상위클래스가 던지는 예외클래스 또는 자식예외클래스만 던질 수 있음.
            //throw new Exception(); 상위 클래스(Runnable)에서 예외를 던지지않으므로 컴파일 에러 발생
        }
    }
}

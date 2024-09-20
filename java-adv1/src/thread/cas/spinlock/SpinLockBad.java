package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

//락 구현 실패예시
public class SpinLockBad {

    private volatile boolean lock = false;

    public void lock() {
        log("락 획득시도");
        while(true) {
            if(!lock) { // 1. 락 사용 여부 확인
                sleep(100); // 문제 상황 확인용, 스레드 대기
                lock = true;    // 2.락의 값 변경
                break;
            } else {
                //락을 획득할때까지 스핀대기(바쁜대기) 한다. * 바쁜대기 : CPU자원을 계속 사용하면서 대기함을 의미
                log("락 획득실패 - 스핀대기");
            }
        }
        log("락 획득완료");
    }
    public void unlock() {
        lock = false;
        log("락 반납완료");
    }
}

package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

//락 구현 성공예시
public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 획득시도");
        while(!lock.compareAndSet(false, true)) {
            //락을 획득할때까지 스핀대기(바쁜대기) 한다. * 바쁜대기 : CPU자원을 계속 사용하면서 대기함을 의미
            log("락 획득실패 - 스핀대기");
        }
        log("락 획득완료");
    }
    public void unlock() {
        lock.set(false);
        log("락 반납완료");
    }
}

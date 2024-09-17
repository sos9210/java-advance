package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<String>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while(queue.size() == max) {
            log("[put] 큐가 가득 참, 생산자 대기");
            try {
                //wait()을 호출한 스레드는 대기집합에서 WAITING
                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } 
        }
        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");

        //대기 스레드, WAIT -> BLOCKED 대기집합에 있지만 여전히 임계영역에 있으므로 락 획득을위해 BLOCKED상태로 대기한다.
        notify(); //어떤 스레드가 깨어날지는 알수없음
    }

    @Override
    public synchronized String take() {
        while(queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            try {
                //wait()을 호출한 스레드는 대기집합에서 WAITING
                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] 소비자 데이터 획득, notify() 호출");
        //대기 스레드, WAIT -> BLOCKED 대기집합에 있지만 여전히 임계영역에 있으므로 락 획득을위해 BLOCKED상태로 대기한다.
        notify(); //어떤 스레드가 깨어날지는 알수없음
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

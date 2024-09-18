package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

/**
 * 스레드 대기 집합에서 전체 스레드를 깨우는 문제를 스레드 집합 공간을 나눔으로써 해결한다.
 */
public class BoundedQueueV5 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();    //스레드가 대기할 수 있는공간을 생성한다.(생산자)
    private final Condition consumerCondition = lock.newCondition();    //스레드가 대기할 수 있는공간을 생성한다.(소비자)

    private final Queue<String> queue = new ArrayDeque<String>();
    private final int max;

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while(queue.size() == max) {
                log("[put] 큐가 가득 참, 생산자 대기");
                try {
                    producerCondition.await();  //producerCondition에서 생산자 스레드가 대기한다.
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, consumerCondition.signal() 호출");
            consumerCondition.signal(); //consumerCondition에서 대기하고 있던 소비자 스레드를 깨운다.
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    consumerCondition.await(); //consumerCondition에서 소비자 스레드가 대기한다.
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            log("[take] 소비자 데이터 획득, producerCondition.signal() 호출");
            producerCondition.signal(); //condition에서 대기하고 있던 생산자 스레드를 깨운다.
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

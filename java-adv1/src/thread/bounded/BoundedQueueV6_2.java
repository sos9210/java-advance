package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {

    private final BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        //저장에 실패하면 false 성공하면 trye
        boolean result = queue.offer(data);
        log("저장시도결과 = " + result);
    }

    @Override
    public String take() {
        //데이터가 없으면 null
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

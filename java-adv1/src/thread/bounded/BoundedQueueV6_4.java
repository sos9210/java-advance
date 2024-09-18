package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {

    private final BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        //java.lang.IllegalStateException : Queue full
        //남은 데이터 저장공간이 없으면 예외발생
        boolean result = queue.add(data);
    }

    @Override
    public String take() {
        //java.util.NoSuchElementException
        //꺼낼 데이터가 없으면 예외발생
        return queue.remove();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

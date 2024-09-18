package thread.cas.increment;

import java.util.ArrayList;

import static util.ThreadUtils.sleep;

/**
 * 원자적 연산은 더 이상 쪼갤 수 없는 연산.
 * 멀티스레드 환경에서 다른 스레드의 연산에 방해받지 않고 연산을 수행함으로써 데이터 일관성을 보장.
 * ex) a = 1  : 원자적 연산
 *  a++;, a = a + 1  : 비원자적 연산
 */
public class IncrementThreadMain {
    public static final int THREAD_COUNT = 1000;
    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }
    
    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            public void run() {
                sleep(10); //너무빨리 실행되기 때문에 ,다른스레드와 동시실행을위해 잠시 쉰다.
                incrementInteger.increment();
            }
        };
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result : " + result);
    }
}

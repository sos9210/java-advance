package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {
    //AtomicInteger.incrementAndGet() 구현방식
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start Value = " + atomicInteger.get());

        int resultValue1 = incrementAndGet(atomicInteger);

        System.out.println("resultValue1 : " + resultValue1);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            // 다른 스레드가 끼어들어서 값을 변경한경우 기대한 값이 나오지 않을 수 있다.
            getValue = atomicInteger.get();
            log("getValue : " + getValue);

            // 따라서 CAS연산을통해 기대한값이면 값을 증가시키는 방식으로 구현된다.
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result : " + result);
        } while (!result);
        return getValue + 1;
    }
}

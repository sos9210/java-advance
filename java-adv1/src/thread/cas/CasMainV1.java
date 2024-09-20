package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {
    /**
     * CAS연산은 하드웨어(CPU)에서 지원한다.
     * CPU에서 CAS연산을 하는 시간은 아주 찰나의 순간이다.
     * CAS연산을 요청하는동안 CPU는 값의 변경을 막고 expectedValue와 같으면 newValue로 변경한다.
     * 같으면 true, 다르면 false를 반환한다.
     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start Value = " + atomicInteger.get());
        //CAS연산
        boolean result1 = atomicInteger.compareAndSet(0, 1);//값이 0이면 1로 셋팅해라
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1);//값이 0이면 1로 셋팅해라
        System.out.println("result2 = " + result2 + ", value = " + atomicInteger.get());

    }
}

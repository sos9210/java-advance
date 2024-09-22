package thread.collection.simple.list;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

//ArrayList를 간단하게 구현해본 클래스
//synchronized를 사용한 동시성 이슈 해결
public class SyncList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elementData;
    private int size;

    public SyncList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized void add(Object o) {
        elementData[size] = o;
        sleep(100); //멀티스레드 문제를 쉽게 확인하기위한 코드
        size++;
    }

    @Override
    public synchronized Object get(int index) {
        return elementData[index];
    }

    @Override
    public synchronized String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size))
                + " size = " + size + ", capacity = " + elementData.length;
    }
}

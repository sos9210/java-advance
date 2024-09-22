package thread.collection.simple.list;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

//ArrayList를 간단하게 구현해본 클래스
//스레드 세이프 X
public class BasicList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elementData;
    private int size;

    public BasicList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    //add는 원자적으로 수행되지 않는다.
    @Override
    public void add(Object o) {
        elementData[size] = o;
        sleep(100); //멀티스레드 문제를 쉽게 확인하기위한 코드
        size++;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size))
                + " size = " + size + ", capacity = " + elementData.length;
    }
}

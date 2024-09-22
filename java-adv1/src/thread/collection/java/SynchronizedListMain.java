package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {
    public static void main(String[] args) {
        // java.util.Collections.synchronizedList : 자바에서 제공하는 동기화 프록시
        //synchronized 키워드에 사용에 따른 동기화 비용이 발생하여 성능이슈 발생.
        // 따라서 java.util.concurrent 에서 제공하는 클래스를 사용하자..
        List<String> list = Collections.synchronizedList(new ArrayList<>());


        list.add("data1");
        list.add("data2");
        list.add("data3");
        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}

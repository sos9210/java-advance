package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");

        //- 쓰레드객체는 run()이 아닌 start()로 실행해야한다. (쓰레드의 독립적인 수행을 위함)
        //- 쓰레드 스케줄링이 일정하지 않기때문에 실행순서는 달라질 수 있다.
        //- start() 실행함으로써 helloThread의 스택프레임이 생성된다.
        helloThread.start();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}

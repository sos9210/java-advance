package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;

public class MyPrinterV4 {

    //Queue구조의 프린터를 구현해본다.
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q)");
            String input = userInput.nextLine();
            if(input.equals("q")) { //"q"를 입력하면 프린터가 종료된다.
                printerThread.interrupt();  //인터럽트
                break;
            }
            printer.addJob(input);
        }

    }

    static class Printer implements Runnable {
        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            //메인스레드에서 q를 입력(인터럽트)하면 종료된다.
            while (!Thread.interrupted()) {
                try {
                    if (jobQueue.isEmpty()) {
                        Thread.yield(); //다른 스레드에게 cpu자원을 양보한다.
                        continue;
                    }
                    String job = jobQueue.poll();
                    log("출력시작 : " + job + ", 대기문서 : " + jobQueue);
                    Thread.sleep(3000);
                    log("출력완료");
                } catch (InterruptedException e) {
                    log("인터럽트!!");
                    break;
                }
                log("프린터 종료");
            }
        }

        public void addJob(String input) {
            jobQueue.add(input);
        }
    }
}

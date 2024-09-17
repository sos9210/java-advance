package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {

    private int balance;
    private final Lock lock = new ReentrantLock();
    public BankAccountV5(int initialBalance) {
        this.balance = initialBalance;
    }
    // synchronized -> ReentrantLock을 활용한다.
    @Override
    public boolean withdraw(int amount) {

        log("거래 시작 : " + getClass().getSimpleName());

        if(!lock.tryLock()) { // ReentrantLock을 이용하여 lock획득을 시도하고 락획득에 성공하면 true, 이미 락이 사용중이면 false를 반환
            log("[진입 실패] 이미 처리중인 작업이 있습니다.");
            return false;
        }

        try {
            log("[검증시작] 출금액 : " + amount + ", 잔액 : " + balance);
            //잔고가 출금액보다 적으면 진행X
            if (balance < amount) {
                log("[검증실패] 출금액 : " + amount + ", 잔액 : " + balance);
                return false;
            }

            //잔고가 출금액보다 많으면 진행O
            log("[검증완료] 출금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000); //출금에 걸리는 시간으로 가정
            balance -= amount;
            log("[출금완료] 출금액 : " + amount + ", 잔액 : " + balance);
        } finally {
            lock.unlock(); // lock을 획득한 후 반드시 lock을 반납해야한다. )) 임계영역끝
        }

        log("거래 종료 : " + getClass().getSimpleName());
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock(); // ReentrantLock을 이용하여 lock을 건다. )) 임계영역시작
        try {
            return balance;
        } finally {
            lock.unlock(); // lock을 획득한 후 반드시 lock을 반납해야한다. )) 임계영역끝
        }
    }
}

package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {

    private int balance;

    public BankAccountV3(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {

        log("거래 시작 : " + getClass().getSimpleName());

        //synchronized를 사용하여 임계영역으로 선언
        //성능상 이슈를 고려하여 임계영역을 블럭으로 최소화한다.
        synchronized (this) {
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
        }
        log("거래 종료 : " + getClass().getSimpleName());
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}

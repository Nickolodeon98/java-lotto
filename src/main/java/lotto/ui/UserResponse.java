package lotto.ui;

import lotto.Lotto;
import lotto.LottoHierarchy;

import java.util.List;

public class UserResponse {

    private LottoHierarchy lottoHierarchy;

    public UserResponse() {
    }

    public void printNumbersAndLotto(Long price) {
        this.lottoHierarchy = new LottoHierarchy(price);
        System.out.printf("%d개를 구매했습니다.", lottoHierarchy.getCounter());
        List<Lotto> everyLotto = lottoHierarchy.getPrintedLotto();
        for (Lotto lotto : everyLotto) {
            System.out.println(lotto.getNumbers());
        }
    }
}

package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public int generatedLottoNums(long price) {
        if (price % 1000 != 0) throw new IllegalArgumentException();
        return (int) price / 1000;
    }

    public Lotto lottoGenerator() {
        List<Integer> sixNumbers = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int rndNumber = Randoms.pickNumberInRange(1, 45);
            sixNumbers.add(rndNumber); // 중복 검사는 입력에 대해서도 동일하게 진행된다.
        }

        return new Lotto(sixNumbers);
    }

    public List<Lotto> everyLottoGenerator(int repetition, List<Lotto> printedLotto) {
        if (printedLotto.size() == repetition) return printedLotto;

        Lotto lotto = null;

        while (lotto == null) {
            try {
                lotto = lottoGenerator();
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }

        printedLotto.add(lotto);

        return everyLottoGenerator(repetition, printedLotto);
    }

    public boolean isEarnOrLoss(List<Integer> winningNumbers, long price) {
        int repeatNumber = generatedLottoNums(price);
        List<Lotto> severalLotto = new ArrayList<>();
        System.out.println(severalLotto.toString());
        return true;
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현

    }
}

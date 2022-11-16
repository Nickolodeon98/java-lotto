package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 당첨 등수를 찾는 것에 관련된 클래스 */
public class LottoHierarchy {
    private int counter;
    private List<Lotto> printedLotto = new ArrayList<>();

    public LottoHierarchy() {
    }

    public LottoHierarchy(boolean forTest) {
        Lotto lotto1 = new Lotto(List.of(8, 21, 23, 41, 42, 43));
        Lotto lotto2 = new Lotto(List.of(3, 5, 11, 16, 32, 38));
        Lotto lotto3 = new Lotto(List.of(7, 11, 16, 35, 36, 44));
        Lotto lotto4 = new Lotto(List.of(1, 8, 11, 31, 41, 42));
        Lotto lotto5 = new Lotto(List.of(13, 14, 16, 38, 42, 45));
        Lotto lotto6 = new Lotto(List.of(7, 11, 30, 40, 42, 43));
        Lotto lotto7 = new Lotto(List.of(2, 13, 22, 32, 38, 45));
        Lotto lotto8 = new Lotto(List.of(1, 3, 5, 14, 22, 45));
        printedLotto.add(lotto1);
        printedLotto.add(lotto2);
        printedLotto.add(lotto3);
        printedLotto.add(lotto4);
        printedLotto.add(lotto5);
        printedLotto.add(lotto6);
        printedLotto.add(lotto7);
        printedLotto.add(lotto8);
    }

    /* ui 에서 받을 때를 생각해보면, 입력된 숫자 6개를 리스트로 받을 것이다.
     * 이후 받은 리스트와 지출된 금액을 토대로 메서드를 호출할 것이다. */
    public LottoHierarchy(long price) {
        this.counter = generatedLottoNums(price);
        this.printedLotto = everyLottoGenerator(this.counter); // List<Lotto> 타입의 변수이다.
    }

    public int howManyDuplicates(List<Integer> numbers, Lotto lotto) {
        List<Integer> tmpNumbers = new ArrayList<>(numbers);
        tmpNumbers.retainAll(lotto.getNumbers());
        return tmpNumbers.size();
    }

    public Ranks mapDuplicatesToRanks(int duplicatesCount, boolean isBonus) {
        if (duplicatesCount < 3) return Ranks.values()[0];
        if (duplicatesCount != 5) return Ranks.values()[duplicatesCount-2];
        if (isBonus) return Ranks.values()[duplicatesCount];
        return Ranks.values()[duplicatesCount-2];
    }

    /* 겹치는 것을 검사하는 메서드에 everyLottoGenerator 과 winningNumbers 를 태운다. */
    public List<Ranks> rankedWhereAbouts(List<Integer> winningNumbers, int bonus) {
        List<Ranks> statistics = new ArrayList<>();
        Ranks ranking = null;
        boolean isBonus = false;
        int duplicates = 0;

        for (Lotto lotto : printedLotto) {
            duplicates = howManyDuplicates(winningNumbers, lotto);
            isBonus = lotto.getNumbers().contains(bonus);
            ranking = mapDuplicatesToRanks(duplicates, isBonus);
            statistics.add(ranking);

        }
        return statistics;
    }

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
        Collections.sort(sixNumbers);
        return new Lotto(sixNumbers);
    }

    public List<Lotto> everyLottoGenerator(int repetition) {
        if (printedLotto.size() == repetition) return printedLotto;

        Lotto lotto = null;
        while (lotto == null) {
            try {
                lotto = lottoGenerator();
            } catch (IllegalArgumentException e) {
            }
        }
        printedLotto.add(lotto);
        return everyLottoGenerator(repetition);
    }

    public Long earnings(List<Ranks> rankings) {
        Long profit = 0L;
        for (Ranks ranking : rankings) {
            profit += ranking.getValue();
        }
        return profit;
    }

    public void printLotto() {
        for (Lotto lotto : printedLotto) {
            System.out.println(lotto.toString());
        }
    }
}

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

    /* ui 에서 받을 때를 생각해보면, 입력된 숫자 6개를 리스트로 받을 것이다.
     * 이후 받은 리스트와 지출된 금액을 토대로 메서드를 호출할 것이다. */
    public LottoHierarchy(long price) {
        this.counter = generatedLottoNums(price);
        this.printedLotto = everyLottoGenerator(this.counter); // List<Lotto> 타입의 변수이다.
    }

    public int howManyDuplicates(List<Integer> numbers, Lotto lotto) {
        numbers.retainAll(lotto.getNumbers());
        return numbers.size();
    }

    public Ranks mapDuplicatesToRanks(int duplicatesCount, boolean isBonus) {
        if (duplicatesCount == 3) return Ranks.FIFTH_PLACE;
        if (duplicatesCount == 4) return Ranks.FOURTH_PLACE;
        if (duplicatesCount == 5) {
            if (isBonus) return Ranks.SECOND_PLACE;
            return Ranks.THIRD_PLACE;
        }
        if (duplicatesCount == 6) return Ranks.FIFTH_PLACE;
        return Ranks.NONE;
    }

    /* 겹치는 것을 검사하는 메서드에 everyLottoGenerator 과 winningNumbers 를 태운다. */
    public Ranks rankedWhereAbouts(List<Integer> winningNumbers, int bonus) {
        Ranks ranking = null;
        boolean isBonus = false;
        int duplicates = 0;
        for (Lotto lotto : printedLotto) {
            duplicates = howManyDuplicates(winningNumbers, lotto);
            isBonus = lotto.getNumbers().contains(bonus);
            ranking = mapDuplicatesToRanks(duplicates, isBonus);
        }
        return ranking;
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
}

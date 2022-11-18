package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.controller.Error;
import lotto.domain.Ranks;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public List<Lotto> getPrintedLotto() {
        return printedLotto;
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

    public int[] countEachRanks(List<Ranks> rankings) {
        int[] totalRanks = new int[6];
        for (Ranks ranking : rankings) {
            totalRanks[ranking.ordinal()]++; //list.get(2) == 1 [1,2,3,4,5,6]
        }
        return totalRanks;
        // rankings = [NONE, FIRST_PLACE, SECOND_PLACE, NONE]
        // totalRanks = {1,0,0,0,0,0}
        // totalRanks = {1,0,0,0,1,0}
        // totalRanks = {1,0,0,0,1,1}
        // totalRanks = {2,0,0,0,1,1}
    }

    public int generatedLottoNums(long price) {
        if (price % 1000 != 0) {
            System.out.println("[ERROR] " + Error.VALUE_ERROR.getValue());
            throw new IllegalArgumentException();
        }
        return (int) price / 1000;
    }

    public Lotto lottoGenerator() {
        List<Integer> sixNumbers = new ArrayList<>();
        sixNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        sixNumbers = sixNumbers.stream().sorted().collect(Collectors.toList());
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

    public float earnings(List<Ranks> rankings) {
        Long profit = 0L;
        for (Ranks ranking : rankings) {
            profit += ranking.getValue();
        }
        return (float) profit / (counter * 1000) * 100;
    }

    public void printLotto() {
        for (Lotto lotto : printedLotto) {
            System.out.println(lotto.toString());
        }
    }


}

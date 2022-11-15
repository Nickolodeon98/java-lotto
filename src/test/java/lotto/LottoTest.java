package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 아래에 추가 테스트 작성 가능

    @DisplayName("주어진 금액에 맞는 로또 개수를 찾는다.")
    @Test
    void howManyLotto() {
        LottoHierarchy lottoHierarchy = new LottoHierarchy();
        Assertions.assertEquals(16, lottoHierarchy.generatedLottoNums(16000));
    }

//    @DisplayName("무작위의 중복 없는 6개의 숫자를 포함하는 리스트를 생성한다.")
//    @Test
//    void generateLotto() {
//        List<Integer> sixNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6)).lottoGenerator();
//        Assertions.assertEquals(sixNumbers.stream().distinct().count(), sixNumbers.size());
//    }

    @DisplayName("주어진 개수만큼 로또를 생성한다.")
    @Test
    void generateANumberOfLotto() {
        LottoHierarchy lottoHierarchy = new LottoHierarchy(8000);
        List<Lotto> printedLotto = new ArrayList<>();
        for (Lotto lotto : lottoHierarchy.everyLottoGenerator(8)) {
            System.out.println(lotto.toString());
        }
    }
//
//    @DisplayName("등수를 정확히 판단한다.")
//    @Test
//    void findInWhichRanking() {
//        LottoHierarchy lottoHierarchy = new LottoHierarchy(8000);
//        List<Integer> winningNumbers = List.of(1,2,3,4,5,6);
//        Assertions.assertEquals(1, lottoHierarchy.rankedWhereAbouts(winningNumbers));
//    }

    @DisplayName("중복되는 원소의 개수를 정확히 판단한다.")
    @Test
    void findNumberOfDuplicates() {
        LottoHierarchy lottoHierarchy = new LottoHierarchy();
        List<Integer> toCompare = new ArrayList<>();
        toCompare.add(1);
        toCompare.add(2);
        toCompare.add(3);
        toCompare.add(4);
        toCompare.add(5);
        toCompare.add(6);

        List<Integer> toBeCompared = new ArrayList<>();
        toBeCompared.add(1);
        toBeCompared.add(3);
        toBeCompared.add(5);
        toBeCompared.add(14);
        toBeCompared.add(22);
        toBeCompared.add(45);
        Lotto lotto = new Lotto(toBeCompared);

        Assertions.assertEquals(3, lottoHierarchy.howManyDuplicates(toCompare, lotto));
    }
}

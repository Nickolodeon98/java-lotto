package lotto;

import lotto.domain.Lotto;
import lotto.domain.Ranks;
import lotto.service.LottoHierarchy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    LottoHierarchy lottoHierarchy;
    List<Integer> winningNumbers;
    @BeforeEach
    void SetUp() {
         lottoHierarchy = new LottoHierarchy();
         winningNumbers= new ArrayList<>();
         winningNumbers.add(1);
         winningNumbers.add(2);
         winningNumbers.add(3);
         winningNumbers.add(4);
         winningNumbers.add(5);
         winningNumbers.add(6);
    }

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
        Assertions.assertEquals(16, lottoHierarchy.generatedLottoNums(16000));
    }

//    @DisplayName("무작위의 중복 없는 6개의 숫자를 포함하는 리스트를 생성한다.") // 그런 기능 없음
//    @Test
//    void generateLotto() {
//        LottoHierarchy lottoHierarchy = new LottoHierarchy();
//        Lotto sixNumbers = lottoHierarchy.lottoGenerator();
//        Assertions.assertEquals(sixNumbers.getNumbers().stream().distinct().count(), sixNumbers.getNumbers().size());
//    }

    @DisplayName("주어진 개수만큼 로또를 생성한다.")
    @Test
    void generateANumberOfLotto() {
        List<Lotto> severalLotto = lottoHierarchy.everyLottoGenerator(8);
        for (Lotto lotto : severalLotto) {
            System.out.println(lotto.toString());
        }
        Assertions.assertEquals(8, severalLotto.size());
    }

    @DisplayName("중복되는 원소의 개수를 정확히 판단한다.")
    @Test
    void findNumberOfDuplicates() {
        List<Integer> toBeCompared = new ArrayList<>();
        toBeCompared.add(1);
        toBeCompared.add(3);
        toBeCompared.add(5);
        toBeCompared.add(14);
        toBeCompared.add(22);
        toBeCompared.add(45);
        Lotto lotto = new Lotto(toBeCompared);

        Assertions.assertEquals(3, lottoHierarchy.howManyDuplicates(winningNumbers, lotto));
    }

    @DisplayName("당첨 등수의 목록을 정확히 반환한다.")
    @Test
    void getStatistics() {
        LottoHierarchy lottoHierarchy = new LottoHierarchy(true);
        List<Ranks> ranks = lottoHierarchy.rankedWhereAbouts(winningNumbers, 7);
        Assertions.assertTrue(ranks.contains(Ranks.FIFTH_PLACE));
    }

    List<Ranks> getRankings() {
        LottoHierarchy lottoHierarchy = new LottoHierarchy(true);
        return lottoHierarchy.rankedWhereAbouts(winningNumbers, 7);
    }

    @DisplayName("얼마를 벌었는지 정확히 금액을 토대로 수익률을 반환한다.")
    @Test
    void earnHowMuch() {
        lottoHierarchy.setCounter(8);
        Assertions.assertEquals(62.5f, lottoHierarchy.earnings(getRankings()));
    }
}

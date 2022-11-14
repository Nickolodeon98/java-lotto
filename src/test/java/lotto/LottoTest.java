package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(16, new Lotto(List.of(1, 2, 3, 4, 5, 6)).generatedLottoNums(16000));
    }

    @DisplayName("무작위의 중복 없는 6개의 숫자를 포함하는 리스트를 생성한다.")
    @Test
    void generateLotto() {
        List<Integer> sixNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6)).lottoGenerator();
        Assertions.assertEquals(sixNumbers.stream().distinct().count(), sixNumbers.size());
    }
}

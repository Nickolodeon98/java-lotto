package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    public int generatedLottoNums(long price) {
        if (price % 1000 != 0) throw new IllegalArgumentException();
        return (int) price / 1000;
    }

    public List<Integer> lottoGenerator() {
        List<Integer> singleLotto = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int rndNumber = Randoms.pickNumberInRange(1, 45);
            if (!singleLotto.contains(rndNumber)) singleLotto.add(rndNumber); // 중복 검사는 입력에 대해서도 동일하게 진행된다.
        }
        return singleLotto;
    }

}

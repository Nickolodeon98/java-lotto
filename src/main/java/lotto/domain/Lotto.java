package lotto.domain;

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
        if (numbers.stream().distinct().count() != numbers.size()) { // 중복인 숫자가 있는 경우의 예외 처리
            throw new IllegalArgumentException(); // 또 검사한다.
        }
    }

    @Override
    public String toString() {
        return "numbers=" + numbers;
    }
    // TODO: 추가 기능 구현

    public List<Integer> getNumbers() {
        return numbers;
    }

}

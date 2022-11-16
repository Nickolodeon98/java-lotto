package lotto.controller;

public enum Error {

    RANGE_ERROR("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    WHITESPACE_ERROR("로또 번호에 공백이 포함되어 있습니다."),
    NUMERIC_ERROR("로또 번호에 숫자가 아닌 문자가 포함되어 있습니다."),
    VALUE_ERROR("로또는 1000원 단위로만 구매할 수 있습니다."),
    FORMAT_ERROR("금액에 숫자가 아닌 값이 포함되어 있습니다.");
    private final String value;

    Error(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

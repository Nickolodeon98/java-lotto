package lotto;

public enum Ranks {
    /* 변하지 않는 값을 Enum 을 적용해 표현할 수 있다. */
    NONE(0L),
    FIFTH_PLACE(5000L),
    FOURTH_PLACE(50000L),
    THIRD_PLACE(1500000L),
    FIRST_PLACE(3000000L),
    SECOND_PLACE(2000000000L);

    private final Long value;

    Ranks(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}

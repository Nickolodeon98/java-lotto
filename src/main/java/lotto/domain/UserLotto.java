package lotto.domain;

import lotto.controller.Error;

import java.util.ArrayList;
import java.util.List;

public class UserLotto {

    private List<Integer> winningLotto;

    private int bonus;
    private Error error = null;
    public UserLotto(String winner) {
        this.winningLotto = new ArrayList<>();
        validate(winner);
        generate(winner);
    }

    public void recordBonus(String bonus) {
        validateBonus(bonus);
        generateBonus(bonus);
    }

    public List<Integer> getWinningLotto() {
        return winningLotto;
    }

    public Error getError() {
        return error;
    }

    public int getBonus() {
        return bonus;
    }

    public void validateBonus(String bonus) {
        if (Integer.parseInt(bonus) > 45 || Integer.parseInt(bonus) < 1)
            this.error = Error.RANGE_ERROR;
        if (!bonus.matches("[0-9]*"))
            this.error = Error.NUMERIC_ERROR;
        if (bonus.contains(" "))
            this.error = Error.WHITESPACE_ERROR;
    }

    public void generateBonus(String bonus) {
        this.bonus = Integer.parseInt(bonus);
    }
    public void generate(String input) {
        String[] each = input.split(",");
        for (String number : each) {
            int member = Integer.parseInt(number);
            if (member > 45 || member < 1) {
                this.error = Error.RANGE_ERROR;
            }
            this.winningLotto.add(member);
        }
    }

    public void validate(String input) {
        if (input.contains(" ")) {
            this.error = Error.WHITESPACE_ERROR;
        }
        if (!input.matches("([0-9]*,){5}[0-9]*")) {
            this.error = Error.NUMERIC_ERROR;
        }
    }
}

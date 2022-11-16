package lotto.controller;

import camp.nextstep.edu.missionutils.Console;

public class UserInterface {

    private final UserResponse userResponse;
    public UserInterface() {
        this.userResponse = new UserResponse();
    }
    public void buy() {
        System.out.println("구입금액을 입력해 주세요.");
        String consumed = Console.readLine();
        userResponse.printNumbersAndLotto(consumed); // 주어진 금액으로 초기화한다.
    }

    public void register() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winner = Console.readLine();
        userResponse.constructSystem(winner);
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonus = Console.readLine();
        userResponse.registerBonus(bonus);
        userResponse.printStatistics();
    }



}

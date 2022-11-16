package lotto.ui;

import camp.nextstep.edu.missionutils.Console;

public class UserInterface {

    private UserResponse userResponse;

    public UserInterface() {
        this.userResponse = new UserResponse();
    }
    public void start() {
        String consumed = Console.readLine();
        System.out.println("구입금액을 입력해 주세요.");
        userResponse.printNumbersAndLotto(Long.parseLong(consumed)); // 주어진 금액으로 초기화한다.
    }

//    public void


}

package lotto.view;

import lotto.controller.UserInterface;

public class Application {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.buy();
        ui.register();
    }
}

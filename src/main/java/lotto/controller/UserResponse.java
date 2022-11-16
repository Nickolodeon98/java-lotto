package lotto.controller;

import lotto.domain.Lotto;
import lotto.service.LottoHierarchy;
import lotto.domain.Ranks;
import lotto.domain.UserLotto;

import java.util.List;

public class UserResponse {

    private LottoHierarchy lottoHierarchy;

    private UserLotto userLotto;

    public UserResponse() {
        this.lottoHierarchy = null;
        this.userLotto = null;
    }

    public void printNumbersAndLotto(String price) {
        if (!price.matches("[0-9]*")) printErrorMessage(Error.FORMAT_ERROR);
        this.lottoHierarchy = new LottoHierarchy(Long.parseLong(price));
        System.out.printf("%d개를 구매했습니다.\n", lottoHierarchy.getCounter());
        List<Lotto> everyLotto = lottoHierarchy.getPrintedLotto();
        for (Lotto lotto : everyLotto) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void constructSystem(String winner) {
        this.userLotto = new UserLotto(winner);
        if (userLotto.getError() != null) printErrorMessage(userLotto.getError());
    }

    public void registerBonus(String bonus) {
        this.userLotto.recordBonus(bonus);
    }

    public void printStatistics() {
        List<Ranks> rankings = lottoHierarchy.rankedWhereAbouts(userLotto.getWinningLotto(), userLotto.getBonus());
        System.out.println("당첨 통계");
        System.out.println("---");
        int[] rankInfo = lottoHierarchy.countEachRanks(rankings);
        System.out.printf("3개 일치 (5,000원) - %d개\n",rankInfo[1]);
        System.out.printf("4개 일치 (50,000원) - %d개\n",rankInfo[2]);
        System.out.printf("5개 일치 (1,500,000원) - %d개\n",rankInfo[3]);
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n",rankInfo[5]);
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n",rankInfo[4]);
        float profitRatio = lottoHierarchy.earnings(rankings);
        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRatio);
    }


    public void printErrorMessage(Error error) {
        System.out.println("[ERROR] " + error.getValue());
        throw new IllegalArgumentException();
    }
}

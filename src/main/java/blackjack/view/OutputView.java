package blackjack.view;

import blackjack.model.card.Cards;
import blackjack.model.game.Result;
import blackjack.model.game.Rule;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printDealInitialCardsResult(final Dealer dealer, final List<User> users, final Rule rule) {
        String userNames = users.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println(dealer.getName() + "와 " + userNames + "에게 2장을 나누었습니다.");
        System.out.println(dealer.getName() + "카드: " + formatCards(rule.openDealerCards(dealer)));
        users.forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(final Player player) {
        System.out.println(player.getName() + "카드: " + formatCards(player.getCards()));
    }

    private String formatCards(final Cards cards) {
        return cards.getValues()
                .stream()
                .map(card -> card.cardNumber().getName() + card.cardType().getName())
                .collect(Collectors.joining(", "));
    }

    public void printDealerDrawnMoreCards(boolean isDrawn) {
        System.out.println();
        if (isDrawn) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
            return;
        }
        System.out.println("딜러는 한장의 카드를 더 받지 않았습니다." + System.lineSeparator());
    }

    public void printOptimalPoints(final Dealer dealer, final List<User> users, final Rule rule) {
        System.out.println(
                dealer.getName() + "카드: " + formatCards(dealer.getCards()) + " - 결과: " + rule.calculateOptimalPoint(
                        dealer));
        users.forEach(user -> System.out.println(
                user.getName() + "카드: " + formatCards(user.getCards()) + " - 결과: " + rule.calculateOptimalPoint(user)));
        System.out.println();
    }

    public void printGameResult(final Map<Player, Map<Result, Integer>> playerListMap) {
        System.out.println("## 최종 승패");
        playerListMap.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + formatResults(entry.getValue()))
                .forEach(System.out::println);
    }

    private String formatResults(Map<Result, Integer> resultStatistics) {
        boolean hasMultipleResults = resultStatistics.values().stream().mapToInt(integer -> integer).sum() > 1;
        if (hasMultipleResults) {
            return resultStatistics.entrySet().stream()
                    .filter(entry -> entry.getValue() > 0)
                    .map(entry -> entry.getValue() + entry.getKey().getName())
                    .collect(Collectors.joining(" "));
        } else {
            return resultStatistics.entrySet().stream()
                    .filter(entry -> entry.getValue() > 0)
                    .map(entry -> entry.getKey().getName())
                    .collect(Collectors.joining());
        }
    }
}

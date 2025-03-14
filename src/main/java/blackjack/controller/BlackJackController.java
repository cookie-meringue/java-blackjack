package blackjack.controller;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.dealer.judgement.JudgementStrategy;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public final class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    private final JudgementStrategy judgementStrategy;
    private final CardDeckInitializer cardDeckInitializer;

    public BlackJackController(
            final InputView inputView,
            final OutputView outputView,
            final JudgementStrategy judgementStrategy,
            final CardDeckInitializer cardDeckInitializer
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.judgementStrategy = judgementStrategy;
        this.cardDeckInitializer = cardDeckInitializer;
    }

    public void run() {
        Dealer dealer = new Dealer(judgementStrategy, cardDeckInitializer);
        List<Player> players = makePlayers();

        dealInitialCards(dealer, players);
        drawMoreCards(dealer, players);
        fight(dealer, players);
        printGameResults(dealer, players);
    }

    private List<Player> makePlayers() {
        return inputView.readUserNames()
                .stream()
                .map(name -> new Player(name, inputView.readBettingMoney(name)))
                .toList();
    }

    private void dealInitialCards(final Dealer dealer, final List<Player> players) {
        dealer.dealInitialCards(players);
        outputView.printDealInitialCardsResult(dealer, players);
    }

    private void drawMoreCards(final Dealer dealer, final List<Player> players) {
        players.forEach(player -> drawMorePlayerCards(dealer, player));
        outputView.printDealerDrawnMoreCards(dealer.drawSelf());
    }

    private void fight(final Dealer dealer, final List<Player> players) {
        for (Player player : players) {
            dealer.fight(player);
        }
    }

    private void drawMorePlayerCards(final Dealer dealer, final Player player) {
        while (player.canReceiveMoreCard() && inputView.readUserDrawMoreCard(player)) {
            dealer.dealPlayerCards(player);
            outputView.printPlayerCards(player);
        }
    }

    private void printGameResults(final Dealer dealer, final List<Player> players) {
        outputView.printOptimalPoints(dealer, players);
        outputView.printGameResult(players);
    }
}

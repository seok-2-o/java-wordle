package presentation;

import domain.Word;
import domain.Wordle;
import infra.TextFileWordProvider;
import infra.WordProvider;
import presentation.ui.ConsoleView;

public class GameApplication {

    public static void main(String[] args) {

        ConsoleView.welcome();
        WordProvider provider = new TextFileWordProvider();
        Word today = Word.todayWorld(provider);
        Wordle game = new Wordle(today);

        while (!game.isEnd()) {
            String input = ConsoleView.ask();
            try {
                Word answer = new Word(input, provider);
                game.attempt(answer);
            } catch (IllegalArgumentException e) {
                ConsoleView.error(e.getMessage(), input);
                continue;
            }
            ConsoleView.render(game.getLastAttempt());
        }

        ConsoleView.render(game.getAllAttempt());
        ConsoleView.printTodayWord(today.toString());

    }

}

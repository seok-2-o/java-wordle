package helper;

import infra.WordProvider;
import java.util.List;

public class FakeWordProvider implements WordProvider {

    private List<String> words = List.of("spill","lilps", "ddddd", "shops");

    @Override
    public String getTodayWord() {
        return null;
    }

    @Override
    public boolean contains(String word) {
        return word != null && words.contains(word);
    }
}

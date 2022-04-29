package domain;

import infra.WordProvider;
import java.util.Arrays;
import java.util.List;

public class Word {

    private List<String> word;

    public Word(String word, WordProvider provider) {
        if(!provider.contains(word)) {
            throw new IllegalArgumentException("등록된 단어만 생성 가능합니다.");
        }
        this.word = Arrays.asList(word.toLowerCase().split(""));;
    }

}

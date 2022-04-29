package domain;

import domain.Matches.Type;
import infra.WordProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Word {

    private static final int MAX_WORD_SIZE = 5;

    private List<String> word;

    private Word(String word) {
        this.word = Arrays.asList(word.toLowerCase().split(""));
    }

    public Word(String word, WordProvider provider) {
        if(!provider.contains(word)) {
            throw new IllegalArgumentException("등록된 단어만 생성 가능합니다.");
        }
        this.word = Arrays.asList(word.toLowerCase().split(""));
    }

    public static Word todayWorld(WordProvider provider) {
        return new Word(provider.getTodayWord());
    }

    public Matches match(Word other) {
        if(this.word.equals(other.word)) {
            return Matches.PERFECT;
        }
        List<String> temp = new ArrayList<>(this.word);
        Matches.Type[] result = new Matches.Type[MAX_WORD_SIZE];
        Arrays.fill(result, Type.MISS);

        for (int idx = 0; idx < MAX_WORD_SIZE; idx++) {
            String current = other.word.get(idx);
            if (current.equals(this.word.get(idx))) {
                result[idx] = Matches.Type.MATCH;
                temp.remove(current);
            }
        }

        for (int idx = 0; idx < MAX_WORD_SIZE; idx++) {
            if (result[idx] != Matches.Type.MATCH && temp.remove(other.word.get(idx))) {
                result[idx] = Matches.Type.EXIST;
            }
        }
        return new Matches(List.of(result));
    }
}

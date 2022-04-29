package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.ref.WeakReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WordTest {

    private static final Word APPLE = new Word("apple", word -> true);

    @DisplayName("정답과 답안은 단어 목에 존재하는 단어여야 한다.")
    @Test
    void create() {
        assertThatThrownBy(() -> new Word("apple", word -> false))
            .isInstanceOf(IllegalArgumentException.class);
    }



}
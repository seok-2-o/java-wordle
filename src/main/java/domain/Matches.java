package domain;

import static domain.Matches.Type.MATCH;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Matches {


    public enum Type {
        MATCH,
        EXIST,
        MISS
    }

    public static final Matches PERFECT = new Matches(List.of(MATCH, MATCH, MATCH, MATCH, MATCH));

    private final List<Type> elements;

    public Matches(List<Type> elements) {
        this.elements = elements;
    }

    public boolean isAllMatch() {
        return elements.stream()
            .allMatch(it -> it.equals(MATCH));
    }

    public Stream<Type> stream() {
        return this.elements.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matches)) {
            return false;
        }
        Matches matches = (Matches) o;
        return Objects.equals(elements, matches.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return "Matches{" +
            "elements=" + elements +
            '}';
    }
}

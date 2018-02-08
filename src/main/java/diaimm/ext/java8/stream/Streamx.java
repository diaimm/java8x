package diaimm.ext.java8.stream;

import diaimm.ext.java8.Functions;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author diaimm
 * @since 2018. 2. 9.
 */
public final class Streamx {
    private Streamx() {
        throw new UnsupportedOperationException("initiation is not allowed");
    }

    public static <T> Stream<T> stream(Optional<T> opt) {
        return opt.map(Stream::of)
                .orElseGet(Stream::empty);
    }

    public static <T> Stream<T> flatten(Stream<Stream<T>> src) {
        return src.flatMap(s -> s);
    }

    public static <T> Stream<T> flatten(Collection<Stream<T>> src) {
        return src.stream().flatMap(s -> s);
    }

    public static <T1, T2> Stream<Functions.Func2.Tuple<T1, T2>> zip(Stream<T1> s1, Stream<T2> s2) {
        Iterator<T1> it1 = Optional.ofNullable(s1).orElseGet(Stream::empty).iterator();
        Iterator<T2> it2 = Optional.ofNullable(s2).orElseGet(Stream::empty).iterator();

        List<Functions.Func2.Tuple<T1, T2>> result = new ArrayList<>();
        while (it1.hasNext() || it2.hasNext()) {
            result.add(new Functions.Func2.Tuple<>(
                    Optional.of(it1).filter(Iterator::hasNext).map(Iterator::next).orElse(null),
                    Optional.of(it2).filter(Iterator::hasNext).map(Iterator::next).orElse(null)
            ));
        }

        return result.stream();
    }
}

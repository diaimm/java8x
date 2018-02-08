package diaimm.ext.java8;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author diaimm
 * @since 2018. 2. 8.
 */
public class Functions {
    public static interface Func0<R> {
        public R apply();
    }

    public static interface Func1<T, R> {
        public R apply(T t);
    }

    public static interface Func2<T1, T2, R> {
        R apply(T1 t1, T2 t2);

        @RequiredArgsConstructor
        @Getter
        public static class Tuple<T1, T2> {
            private final T1 t1;
            private final T2 t2;
        }
    }

    public static interface Func3<T1, T2, T3, R> {
        public R apply(T1 t1, T2 t2, T3 t3);

        @RequiredArgsConstructor
        @Getter
        public static class Tuple<T1, T2, T3> {
            private final T1 t1;
            private final T2 t2;
            private final T3 t3;
        }
    }

    public static interface Func4<T1, T2, T3, T4, R> {
        public R apply(T1 t1, T2 t2, T3 t3, T4 t4);

        @RequiredArgsConstructor
        @Getter
        public static class Tuple<T1, T2, T3, T4> {
            private final T1 t1;
            private final T2 t2;
            private final T3 t3;
            private final T4 t4;
        }
    }

    public static interface Func5<T1, T2, T3, T4, T5, R> {
        public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);

        @RequiredArgsConstructor
        @Getter
        public static class Tuple<T1, T2, T3, T4, T5> {
            private final T1 t1;
            private final T2 t2;
            private final T3 t3;
            private final T4 t4;
            private final T5 t5;
        }
    }
}

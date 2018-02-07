package diaimm.ext.java8;

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
        public R apply(T1 t1, T2 t2);
    }
}

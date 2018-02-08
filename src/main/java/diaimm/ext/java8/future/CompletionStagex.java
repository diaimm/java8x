package diaimm.ext.java8.future;

import diaimm.ext.java8.Functions;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * @author diaimm
 * @since 2018. 2. 8.
 */
public final class CompletionStagex extends CompletableFuture {
    private CompletionStagex(){
        throw new UnsupportedOperationException("initiation is not allowed");
    }
    /**
     * converts List of Futures to Future of List
     *
     * @param futures
     * @param <T>
     * @return
     */
    public static <T> CompletionStage<List<T>> all(List<CompletionStage<T>> futures) {
        return CompletableFuture
                .allOf(futures
                        .stream()
                        .map(CompletionStage::toCompletableFuture)
                        .collect(Collectors.toList())
                        .toArray(new CompletableFuture[0]))
                .thenApply(done -> futures.stream()
                        .map(CompletionStage::toCompletableFuture)
                        .map(f -> {
                            try {
                                return (T) f.get();
                            } catch (Exception e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
    }

    /**
     * Create a new future finished with an exception
     *
     * @param e   exception
     * @param <T> type
     * @return CompletionStage exceptionally finished with e
     */
    public static <T> CompletionStage<T> exceptionallyCompleted(Exception e) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        result.completeExceptionally(e);
        return result;
    }

    public static <T1, T2> CompletionStage<Functions.Func2.Tuple<T1, T2>> zip(
            CompletionStage<T1> willBeT1, CompletionStage<T2> willBeT2) {
        return willBeT1.thenCombine(willBeT2, Functions.Func2.Tuple::new);
    }

    public static <T1, T2, T3> CompletionStage<Functions.Func3.Tuple<T1, T2, T3>> zip(
            CompletionStage<T1> willBeT1, CompletionStage<T2> willBeT2, CompletionStage<T3> willBeT3) {
        return zip(zip(willBeT1, willBeT2), willBeT3)
                .thenApply(t -> new Functions.Func3.Tuple<>(t.getT1().getT1(), t.getT1().getT2(), t.getT2()));
    }

    public static <T1, T2, T3, T4> CompletionStage<Functions.Func4.Tuple<T1, T2, T3, T4>> zip(
            CompletionStage<T1> willBeT1, CompletionStage<T2> willBeT2,
            CompletionStage<T3> willBeT3, CompletionStage<T4> willBeT4) {
        return zip(zip(willBeT1, willBeT2, willBeT3), willBeT4)
                .thenApply(t -> new Functions.Func4.Tuple<>(t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT2()));
    }

    public static <T1, T2, T3, T4, T5> CompletionStage<Functions.Func5.Tuple<T1, T2, T3, T4, T5>> zip(
            CompletionStage<T1> willBeT1, CompletionStage<T2> willBeT2,
            CompletionStage<T3> willBeT3, CompletionStage<T4> willBeT4,
            CompletionStage<T5> willBeT5) {
        return zip(zip(willBeT1, willBeT2, willBeT3, willBeT4), willBeT5)
                .thenApply(t -> new Functions.Func5.Tuple<>(t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT1().getT4(), t.getT2()));
    }
}

package diaimm.ext.java8.future;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author diaimm
 * @since 2018. 2. 8.
 */
public class CompletionStagex extends CompletableFuture {
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

    public static <T> CompletableFuture<T> exceptionally(Exception e) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        result.completeExceptionally(e);
        return result;
    }
}

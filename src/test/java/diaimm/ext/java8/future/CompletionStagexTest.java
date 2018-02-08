package diaimm.ext.java8.future;

import diaimm.ext.java8.Functions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static diaimm.ext.java8.future.CompletionStagex.all;


/**
 * @author diaimm
 * @since 2018. 2. 8.
 */
public class CompletionStagexTest {
    @Test
    public void all_success() {
        CompletionStage<String> f1 = CompletionStagex.completedFuture("1");
        CompletionStage<String> f2 = CompletionStagex.completedFuture("2");

        try {
            List<String> results = all(Stream.of(f1, f2).collect(Collectors.toList()))
                    .toCompletableFuture().get();

            Assert.assertEquals(2, results.size());
            Assert.assertEquals("1", results.get(0));
            Assert.assertEquals("2", results.get(1));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void all_exception() {
        CompletionStage<String> f1 = CompletionStagex.completedFuture("1");
        CompletionStage<String> f2 = CompletionStagex.exceptionallyCompleted(new NullPointerException("test"));

        try {
            all(Stream.of(f1, f2).collect(Collectors.toList()))
                    .toCompletableFuture().get();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ExecutionException);
            Assert.assertTrue(e.getCause() instanceof NullPointerException);
            Assert.assertEquals("test", e.getCause().getMessage());
        }
    }

    @Test
    public void exceptionallyTest() {
        try {
            CompletionStagex.exceptionallyCompleted(new NullPointerException("test"))
                    .toCompletableFuture()
                    .join();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CompletionException);
            Assert.assertTrue(e.getCause() instanceof NullPointerException);
            Assert.assertEquals("test", e.getCause().getMessage());
        }
    }

    @Test
    public void zip2Test() {
        try {
            Assert.assertEquals("test",
                    CompletionStagex
                            .zip(CompletionStagex.completedFuture("1"), CompletionStagex.completedFuture(1))
                            .thenApply(tuple2 -> "test")
                            .toCompletableFuture()
                            .get());
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            CompletionStagex
                    .zip(CompletionStagex.completedFuture("1"), CompletionStagex.exceptionallyCompleted(new NullPointerException("test")))
                    .thenApply(tuple2 -> "test")
                    .toCompletableFuture()
                    .get();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ExecutionException);
            Assert.assertTrue(e.getCause() instanceof NullPointerException);
            Assert.assertEquals("test", e.getCause().getMessage());
        }
    }

    @Test
    public void zip3Test() {
        try {
            Assert.assertEquals("test",
                    CompletionStagex
                            .zip(CompletionStagex.completedFuture("1"),
                                    CompletionStagex.completedFuture(1),
                                    CompletionStagex.completedFuture(1L))
                            .thenApply(tuple3 -> "test")
                            .toCompletableFuture()
                            .get());
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            CompletionStagex
                    .zip(CompletionStagex.completedFuture("1"),
                            CompletionStagex.completedFuture(1),
                            CompletionStagex.exceptionallyCompleted(new NullPointerException("test")))
                    .thenApply(tuple2 -> "test")
                    .toCompletableFuture()
                    .get();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ExecutionException);
            Assert.assertTrue(e.getCause() instanceof NullPointerException);
            Assert.assertEquals("test", e.getCause().getMessage());
        }
    }

    @Test
    public void zip4Test() {
        try {
            Assert.assertEquals("test",
                    CompletionStagex
                            .zip(CompletionStagex.completedFuture("1"),
                                    CompletionStagex.completedFuture(1),
                                    CompletionStagex.completedFuture(1L),
                                    CompletionStagex.completedFuture(1))
                            .thenApply(tuple -> "test")
                            .toCompletableFuture()
                            .get());
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            CompletionStagex
                    .zip(CompletionStagex.completedFuture("1"),
                            CompletionStagex.completedFuture(1),
                            CompletionStagex.completedFuture(1L),
                            CompletionStagex.exceptionallyCompleted(new NullPointerException("test")))
                    .thenApply(tuple2 -> "test")
                    .toCompletableFuture()
                    .get();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ExecutionException);
            Assert.assertTrue(e.getCause() instanceof NullPointerException);
            Assert.assertEquals("test", e.getCause().getMessage());
        }
    }
}

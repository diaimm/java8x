package diaimm.ext.java8.stream;

import diaimm.ext.java8.Functions;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author diaimm
 * @since 2018. 2. 9.
 */
public class StreamxTest {
    @Test
    public void streamTest() {
        Assert.assertEquals("test",
                Streamx.stream(Optional.of("test"))
                        .collect(Collectors.toList())
                        .get(0));
    }

    @Test
    public void flattenTest() {
        Assert.assertEquals("test",
                Streamx.flatten(Stream.of(Stream.of("test")))
                        .collect(Collectors.toList())
                        .get(0));

        List<Stream<String>> listStream = new ArrayList<>();
        listStream.add(Stream.of("test"));
        Assert.assertEquals("test",
                Streamx.flatten(listStream)
                        .collect(Collectors.toList())
                        .get(0));

        Set<Stream<String>> setStream = new HashSet<>();
        setStream.add(Stream.of("test"));
        Assert.assertEquals("test",
                Streamx.flatten(setStream)
                        .collect(Collectors.toList())
                        .get(0));
    }

    @Test
    public void zipTest() {
        List<Functions.Func2.Tuple<String, Integer>> result = Streamx
                .zip(Stream.of("1"), Stream.of(1, 2))
                .collect(Collectors.toList());

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("1", result.get(0).getT1());
        Assert.assertEquals((Integer) 1, result.get(0).getT2());

        Assert.assertEquals(null, result.get(1).getT1());
        Assert.assertEquals((Integer) 2, result.get(1).getT2());
    }
}

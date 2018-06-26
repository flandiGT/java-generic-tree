package de.flandigt.java.generictree;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

public class TreeTest {

    private Tree<String, Integer> tree;

    @Before
    public void setup() throws Exception {
        tree = new Tree<>();
    }

    @Test
    public void shouldGetNullIfNotPresent() throws Exception {
        TestTreeKey key = new TestTreeKey("key");

        assertThat(tree.search(key), is(nullValue()));
        assertThat(tree.size(), is(equalTo(0)));

        assertThat(tree.iterator().hasNext(), is(false));
    }

    @Test
    public void shouldGetRootValue() throws Exception {
        TestTreeKey key = new TestTreeKey("key");
        int value = 123;

        tree.put(key, value);

        assertThat(tree.search(key), is(equalTo(value)));
        assertThat(tree.size(), is(equalTo(1)));

        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(equalTo(value)));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void shouldGetSecondLevelValue() throws Exception {
        TestTreeKey key = new TestTreeKey("key/value1");
        int value = 123;

        tree.put(key, value);

        assertThat(tree.search(key), is(equalTo(value)));
        assertThat(tree.size(), is(equalTo(1)));

        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(equalTo(value)));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void shouldGetSecondLevelSecondValue() throws Exception {
        TestTreeKey key = new TestTreeKey("key/value1");
        int value = 123;
        TestTreeKey key2 = new TestTreeKey("key/value2");
        int value2 = 5323;

        tree.put(key, value);
        tree.put(key2, value2);

        assertThat(tree.search(key), is(equalTo(value)));
        assertThat(tree.search(key2), is(equalTo(value2)));
        assertThat(tree.size(), is(equalTo(2)));

        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(equalTo(value2)));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(equalTo(value)));
        assertThat(iterator.hasNext(), is(false));
    }


    private static class TestTreeKey implements TreeKey<String> {

        private final String key;

        private TestTreeKey(String key) {
            this.key = key;
        }

        @Override
        public List<String> toKeys() {
            return Arrays.asList(key.split("/"));
        }
    }
}

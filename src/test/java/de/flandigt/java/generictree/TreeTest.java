package de.flandigt.java.generictree;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

@RunWith(NestedRunner.class)
public class TreeTest {

    private Tree<String, Integer> tree;

    @Before
    public void setup() throws Exception {
        tree = new Tree<>();
    }

    public class EmptyTree {

        @Test
        public void sizeShouldBeZero() throws Exception {
            assertThat(tree.size(), is(equalTo(0)));
        }

        @Test
        public void iteratorShouldNotHaveNext() throws Exception {
            assertThat(tree.iterator().hasNext(), is(false));
        }

        @Test
        public void searchingUnknownKeyShouldReturnNull() throws Exception {
            TestTreeKey key = new TestTreeKey("key");
            assertThat(tree.search(key), is(nullValue()));
        }
    }

    public class TreeWithFirstLevelElement {

        private TestTreeKey key;
        private int value;

        @Before
        public void setup() throws Exception {
            key = new TestTreeKey("key");
            value = 123;

            tree.put(key, value);
        }

        @Test
        public void searchingRightKeyShouldGetValue() throws Exception {
            assertThat(tree.search(key), is(equalTo(value)));
        }

        @Test
        public void sizeShouldBeOne() throws Exception {
            assertThat(tree.size(), is(equalTo(1)));
        }

        @Test
        public void iteratorShouldProvideOnlyOneElement() throws Exception {
            Iterator<Integer> iterator = tree.iterator();

            assertThat(iterator.hasNext(), is(true));
            assertThat(iterator.next(), is(equalTo(value)));
            assertThat(iterator.hasNext(), is(false));
        }

        public class WhenRemoveItem {

            Integer removedValue;

            @Before
            public void setup() throws Exception {
                removedValue = tree.remove(key);
            }

            @Test
            public void removedValueShouldEqualValue() throws Exception {
                assertThat(removedValue, is(equalTo(value)));
            }

            @Test
            public void sizeShouldBeZero() throws Exception {
                assertThat(tree.size(), is(equalTo(0)));
            }

            @Test
            public void iteratorShouldNotHaveNext() throws Exception {
                assertThat(tree.iterator().hasNext(), is(false));
            }
        }
    }

    public class TreeWithSecondLevelElement {

        private TestTreeKey key;
        private int value;

        @Before
        public void setup() throws Exception {
            key = new TestTreeKey("key/value");
            value = 5432;

            tree.put(key, value);
        }

        @Test
        public void searchingRightKeyShouldGetValue() throws Exception {
            assertThat(tree.search(key), is(equalTo(value)));
        }

        @Test
        public void sizeShouldBeOne() throws Exception {
            assertThat(tree.size(), is(equalTo(1)));
        }

        @Test
        public void iteratorShouldProvideOnlyOneElement() throws Exception {
            Iterator<Integer> iterator = tree.iterator();

            assertThat(iterator.hasNext(), is(true));
            assertThat(iterator.next(), is(equalTo(value)));
            assertThat(iterator.hasNext(), is(false));
        }

        public class AfterRemoveItem {

            Integer removedValue;

            @Before
            public void setup() throws Exception {
                removedValue = tree.remove(key);
            }

            @Test
            public void removedValueShouldEqualValue() throws Exception {
                assertThat(removedValue, is(equalTo(value)));
            }

            @Test
            public void sizeShouldBeZero() throws Exception {
                assertThat(tree.size(), is(equalTo(0)));
            }

            @Test
            public void iteratorShouldNotHaveNext() throws Exception {
                assertThat(tree.iterator().hasNext(), is(false));
            }
        }

        public class TreeWithSecondElementOnSecondLevel {

            private TestTreeKey secondKey;
            private int secondValue;

            @Before
            public void setup() throws Exception {
                secondKey = new TestTreeKey("key/value2");
                secondValue = 5432;

                tree.put(secondKey, secondValue);
            }

            @Test
            public void searchingFirstKeyShouldGetValue() throws Exception {
                assertThat(tree.search(key), is(equalTo(value)));
            }

            @Test
            public void searchingSecondKeyShouldGetValue() throws Exception {
                assertThat(tree.search(secondKey), is(equalTo(secondValue)));
            }

            @Test
            public void sizeShouldBeOne() throws Exception {
                assertThat(tree.size(), is(equalTo(2)));
            }

            @Test
            public void iteratorShouldProvideOnlyOneElement() throws Exception {
                Iterator<Integer> iterator = tree.iterator();

                assertThat(iterator.hasNext(), is(true));
                assertThat(iterator.next(), is(equalTo(secondValue)));
                assertThat(iterator.hasNext(), is(true));
                assertThat(iterator.next(), is(equalTo(value)));
                assertThat(iterator.hasNext(), is(false));
            }

            public class AfterRemoveFirstItem {

                Integer removedValue;

                @Before
                public void setup() throws Exception {
                    removedValue = tree.remove(key);
                }

                @Test
                public void removedValueShouldEqualValue() throws Exception {
                    assertThat(removedValue, is(equalTo(value)));
                }

                @Test
                public void sizeShouldBeOne() throws Exception {
                    assertThat(tree.size(), is(equalTo(1)));
                }

                @Test
                public void iteratorShouldProvideOnlyOneElement() throws Exception {
                    Iterator<Integer> iterator = tree.iterator();

                    assertThat(iterator.hasNext(), is(true));
                    assertThat(iterator.next(), is(equalTo(value)));
                    assertThat(iterator.hasNext(), is(false));
                }
            }
        }
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

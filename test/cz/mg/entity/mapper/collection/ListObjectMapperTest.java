package cz.mg.entity.mapper.collection;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.entity.mapper.value.IntegerObjectMapper;
import cz.mg.test.Assert;

import java.util.Iterator;

public @Test class ListObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + ListObjectMapperTest.class.getSimpleName() + " ... ");

        ListObjectMapperTest test = new ListObjectMapperTest();
        test.testNullMapping();
        test.testMapping();

        System.out.println("OK");
    }

    private void testNullMapping() {
        Mapper<List<Integer>> mapper = new Mapper<>(new List<>(new ListObjectMapper(), new IntegerObjectMapper()));
        Assert.assertNull(mapper.unmap(mapper.map(null)));
    }

    private void testMapping() {
        test();
        test((Integer)null);
        test(9);
        test(1, 2);
        test(null, 2);
        test(1, null);
        test(4, 5, 7);
        test(null, 5, 7);
        test(4, null, 7);
        test(4, 5, null);
        test(null, 5, null);
        test(null, null, null);
    }

    private void test(Integer... items) {
        Mapper<List<Integer>> mapper = new Mapper<>(new List<>(new ListObjectMapper(), new IntegerObjectMapper()));
        List<Integer> before = new List<>(items);
        List<Integer> after = mapper.unmap(mapper.map(before));
        compare(before, after);
    }

    private void compare(@Mandatory List<Integer> before, @Optional List<Integer> after) {
        Assert.assertNotSame(before, after);
        Assert.assertNotNull(after);
        Assert.assertEquals(before.count(), after.count());
        Iterator<Integer> beforeIterator = before.iterator();
        Iterator<Integer> afterIterator = after.iterator();
        while (beforeIterator.hasNext() && afterIterator.hasNext()) {
            Assert.assertEquals(beforeIterator.next(), afterIterator.next());
        }
    }
}

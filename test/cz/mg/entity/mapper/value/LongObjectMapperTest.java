package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class LongObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + LongObjectMapperTest.class.getSimpleName() + " ... ");

        LongObjectMapperTest test = new LongObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        test(Long.MIN_VALUE);
        test(-800000000000001L);
        test(-9000000L);
        test(-123L);
        test(-7L);
        test(-1L);
        test(0L);
        test(1L);
        test(7L);
        test(123L);
        test(9000000L);
        test(800000000000001L);
        test(Long.MAX_VALUE);
    }

    private void test(@Optional Long value) {
        Mapper<Long> mapper = new Mapper<>(new List<>(new LongObjectMapper()));
        Long after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

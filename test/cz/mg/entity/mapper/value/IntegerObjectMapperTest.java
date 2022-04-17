package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class IntegerObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + IntegerObjectMapperTest.class.getSimpleName() + " ... ");

        IntegerObjectMapperTest test = new IntegerObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        test(Integer.MIN_VALUE);
        test(-9000000);
        test(-123);
        test(-7);
        test(-1);
        test(0);
        test(1);
        test(7);
        test(123);
        test(9000000);
        test(Integer.MAX_VALUE);
    }

    private void test(@Optional Integer value) {
        Mapper<Integer> mapper = new Mapper<>(new List<>(new IntegerObjectMapper()));
        Integer after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

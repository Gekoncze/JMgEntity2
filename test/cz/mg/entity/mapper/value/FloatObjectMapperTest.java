package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class FloatObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + FloatObjectMapperTest.class.getSimpleName() + " ... ");

        FloatObjectMapperTest test = new FloatObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        test(Float.NaN);
        test(Float.MIN_VALUE);
        test(Float.MAX_VALUE);
        test(Float.MIN_NORMAL);
        test(Float.POSITIVE_INFINITY);
        test(Float.NEGATIVE_INFINITY);
        test(-800000000000001F);
        test(-800000000000001.2F);
        test(-9000000F);
        test(-9000000.03F);
        test(-123F);
        test(-123.111F);
        test(-7F);
        test(-7.1F);
        test(-1F);
        test(-1.000009F);
        test(0F);
        test(1F);
        test(1.000009F);
        test(7F);
        test(7.1F);
        test(123F);
        test(123.111F);
        test(9000000F);
        test(9000000.03F);
        test(800000000000001F);
        test(800000000000001.2F);
    }

    private void test(@Optional Float value) {
        Mapper<Float> mapper = new Mapper<>(new List<>(new FloatObjectMapper()));
        Float after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

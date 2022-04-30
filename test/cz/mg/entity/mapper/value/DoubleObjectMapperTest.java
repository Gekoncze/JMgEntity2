package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class DoubleObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + DoubleObjectMapperTest.class.getSimpleName() + " ... ");

        DoubleObjectMapperTest test = new DoubleObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        test(Double.NaN);
        test(Double.MIN_VALUE);
        test(Double.MAX_VALUE);
        test(Double.MIN_NORMAL);
        test(Double.POSITIVE_INFINITY);
        test(Double.NEGATIVE_INFINITY);
        test(-40000000000000000000001.0);
        test(-800000000000001.0);
        test(-800000000000001.2);
        test(-9000000.0);
        test(-9000000.03);
        test(-123.0);
        test(-123.111);
        test(-7.0);
        test(-7.1);
        test(-1.0);
        test(-1.000009);
        test(0.0);
        test(1.0);
        test(1.000009);
        test(7.0);
        test(7.1);
        test(123.0);
        test(123.111);
        test(9000000.0);
        test(9000000.03);
        test(800000000000001.0);
        test(800000000000001.2);
        test(-40000000000000000000001.0);
    }

    private void test(@Optional Double value) {
        Mapper<Double> mapper = new Mapper<>(new List<>(new DoubleObjectMapper()));
        Double after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

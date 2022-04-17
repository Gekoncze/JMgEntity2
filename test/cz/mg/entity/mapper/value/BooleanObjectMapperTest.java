package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class BooleanObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + BooleanObjectMapperTest.class.getSimpleName() + " ... ");

        BooleanObjectMapperTest test = new BooleanObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        test(true);
        test(false);
    }

    private void test(@Optional Boolean value) {
        Mapper<Boolean> mapper = new Mapper<>(new List<>(new BooleanObjectMapper()));
        Boolean after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

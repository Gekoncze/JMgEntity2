package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.common.TestEnum;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class EnumObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + EnumObjectMapperTest.class.getSimpleName() + " ... ");

        EnumObjectMapperTest test = new EnumObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        test(TestEnum.ONE);
        test(TestEnum.TWO);
        test(TestEnum.THREE);
    }

    private void test(@Optional TestEnum value) {
        Mapper<TestEnum> mapper = new Mapper<>(new List<>(new EnumObjectMapper<>(TestEnum.class)));
        TestEnum after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.entity.common.TestEntityWithEnumClass;
import cz.mg.entity.common.TestEnum;

public @Test class MapperFactoryTest {
    public static void main(String[] args) {
        System.out.print("Running " + MapperFactoryTest.class.getSimpleName() + " ... ");

        MapperFactoryTest test = new MapperFactoryTest();
        test.testCreate();

        System.out.println("OK");
    }

    private void testCreate() {
        MapperFactory.getInstance().create().map(1);
        MapperFactory.getInstance().create().map("abc");
        MapperFactory.getInstance().create().map(true);
        MapperFactory.getInstance().create(TestEnum.class).map(TestEnum.THREE);
        MapperFactory.getInstance().create(TestEntityClass.class).map(new TestEntityClass("foo", "bar"));
        MapperFactory.getInstance().create(TestEntityWithEnumClass.class, TestEnum.class)
            .map(new TestEntityWithEnumClass(TestEnum.ONE, "bar"));
    }
}

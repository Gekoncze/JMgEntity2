package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.entity.common.TestEntityComplexClass;
import cz.mg.entity.common.TestEnum;
import cz.mg.test.Assert;

public @Test class MapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + MapperTest.class.getSimpleName() + " ... ");

        MapperTest test = new MapperTest();
        test.testMapping();
        test.testCircularDependency();

        System.out.println("OK");
    }

    private void testMapping() {
        TestEntityClass shared = new TestEntityClass();
        shared.setFoo(null);
        shared.setBar("bar");

        TestEntityComplexClass second = new TestEntityComplexClass();
        second.setValue(11);
        second.setTestEnum(TestEnum.TWO);
        second.setTestEntityClass(shared);
        second.setTestEntityComplexClass(null);

        TestEntityComplexClass first = new TestEntityComplexClass();
        first.setValue(null);
        first.setTestEnum(null);
        first.setTestEntityClass(shared);
        first.setTestEntityComplexClass(second);

        Mapper<TestEntityComplexClass> mapper = MapperFactory.getInstance().create(
            TestEntityComplexClass.class,
            TestEntityClass.class,
            TestEnum.class
        );

        List<Element> elements = mapper.map(first);
        Assert.assertEquals(2, count(elements, TestEntityComplexClass.class.getSimpleName()));
        Assert.assertEquals(1, count(elements, TestEntityClass.class.getSimpleName()));
        Assert.assertEquals(1, count(elements, TestEnum.class.getSimpleName()));

        TestEntityComplexClass after = mapper.unmap(elements);
        compare(first, after);

        Assert.assertNotNull(after);
        Assert.assertNotNull(after.getTestEntityClass());
        Assert.assertNotNull(after.getTestEntityComplexClass());
        Assert.assertNotNull(after.getTestEntityComplexClass().getTestEntityClass());
        Assert.assertSame(after.getTestEntityClass(), after.getTestEntityComplexClass().getTestEntityClass());

        Assert.assertNull(mapper.unmap(mapper.map(null)));
    }

    private int count(@Mandatory List<Element> elements, @Mandatory String name) {
        int count = 0;
        for (Element element : elements) {
            if (element.getName().equals(name)) {
                count++;
            }
        }
        return count;
    }

    private void compare(@Optional TestEntityComplexClass before, @Optional TestEntityComplexClass after) {
        if (before != null) {
            Assert.assertNotNull(after);
            Assert.assertNotSame(before, after);
            Assert.assertEquals(before.getValue(), after.getValue());
            Assert.assertEquals(before.getTestEnum(), after.getTestEnum());
            compare(before.getTestEntityClass(), after.getTestEntityClass());
            compare(before.getTestEntityComplexClass(), after.getTestEntityComplexClass());
        } else {
            Assert.assertNull(after);
        }
    }

    private void compare(@Optional TestEntityClass before, @Optional TestEntityClass after) {
        if (before != null) {
            Assert.assertNotNull(after);
            Assert.assertNotSame(before, after);
            Assert.assertEquals(before.getFoo(), after.getFoo());
            Assert.assertEquals(before.getBar(), after.getBar());
        } else {
            Assert.assertNull(after);
        }
    }

    private void testCircularDependency() {
        TestEntityComplexClass first = new TestEntityComplexClass();
        TestEntityComplexClass second = new TestEntityComplexClass();
        TestEntityComplexClass third = new TestEntityComplexClass();

        first.setTestEntityComplexClass(second);
        second.setTestEntityComplexClass(third);
        third.setTestEntityComplexClass(first);

        Mapper<TestEntityComplexClass> mapper = MapperFactory.getInstance().create(TestEntityComplexClass.class);
        TestEntityComplexClass after = mapper.unmap(mapper.map(first));
        Assert.assertNotNull(after);
        Assert.assertNotSame(after, after.getTestEntityComplexClass());
        Assert.assertSame(after, after.getTestEntityComplexClass().getTestEntityComplexClass().getTestEntityComplexClass());
    }
}

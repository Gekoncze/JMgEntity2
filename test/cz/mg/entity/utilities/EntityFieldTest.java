package cz.mg.entity.utilities;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.test.Assert;

import java.lang.reflect.Method;

public @Test class EntityFieldTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityFieldTest.class.getSimpleName() + " ... ");

        EntityFieldTest test = new EntityFieldTest();
        test.testConstructor();
        test.testGetName();
        test.testGetAndSet();

        System.out.println("OK");
    }

    private void testConstructor() {
        Assert.assertExceptionNotThrown(() -> {
            Method getMethod = TestEntityClass.class.getMethod("getFoo");
            Method setMethod = TestEntityClass.class.getMethod("setFoo", String.class);

            EntityField entityField = new EntityField(getMethod, setMethod);

            Assert.assertEquals(getMethod, entityField.getGetMethod());
            Assert.assertEquals(setMethod, entityField.getSetMethod());
        });
    }

    private void testGetName() {
        Assert.assertExceptionNotThrown(() -> {
            Method getMethod = TestEntityClass.class.getMethod("getFoo");
            Method setMethod = TestEntityClass.class.getMethod("setFoo", String.class);

            EntityField entityField = new EntityField(getMethod, setMethod);

            Assert.assertEquals("Foo", entityField.getName());
        });
    }

    private void testGetAndSet() {
        TestEntityClass testEntity = new TestEntityClass();
        EntityField fooEntityField = getField(TestEntityClass.entity, "Foo");
        EntityField barEntityField = getField(TestEntityClass.entity, "Bar");

        fooEntityField.set(testEntity, "foo1");
        barEntityField.set(testEntity, "bar1");

        Assert.assertEquals("foo1", testEntity.getFoo());
        Assert.assertEquals("bar1", testEntity.getBar());

        testEntity.setFoo("foo2");
        testEntity.setBar("bar2");

        Assert.assertEquals("foo2", fooEntityField.get(testEntity));
        Assert.assertEquals("bar2", barEntityField.get(testEntity));
    }

    private @Mandatory EntityField getField(@Mandatory EntityClass entityClass, @Mandatory String name) {
        for (EntityField entityField : entityClass.getFields()) {
            if (entityField.getName().equals(name)) {
                return entityField;
            }
        }

        throw new RuntimeException("Could not find field '" + name + "'.");
    }
}

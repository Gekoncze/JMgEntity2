package cz.mg.entity.utilities;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.test.Assert;

import java.lang.reflect.Method;

public @Test class EntityFieldTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityFieldTest.class.getSimpleName() + " ... ");

        EntityFieldTest test = new EntityFieldTest();
        test.testConstructor();
        test.testGetName();

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
}

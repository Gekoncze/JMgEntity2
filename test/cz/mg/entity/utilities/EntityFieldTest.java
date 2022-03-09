package cz.mg.entity.utilities;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.test.Assert;

import java.lang.reflect.Method;

public @Test class EntityFieldTest {
    public static void main(String[] args) {
        testConstructor();
        testGetName();
    }

    private static void testConstructor() {
        Assert.assertExceptionNotThrown(() -> {
            Method getMethod = TestEntityClass.class.getMethod("getFoo");
            Method setMethod = TestEntityClass.class.getMethod("setFoo", String.class);

            EntityField entityField = new EntityField(getMethod, setMethod);

            Assert.assertEquals(getMethod, entityField.getGetMethod());
            Assert.assertEquals(setMethod, entityField.getSetMethod());
        });
    }

    private static void testGetName() {
        Assert.assertExceptionNotThrown(() -> {
            Method getMethod = TestEntityClass.class.getMethod("getFoo");
            Method setMethod = TestEntityClass.class.getMethod("setFoo", String.class);

            EntityField entityField = new EntityField(getMethod, setMethod);

            Assert.assertEquals("Foo", entityField.getName());
        });
    }
}

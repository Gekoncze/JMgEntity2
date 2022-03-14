package cz.mg.entity.services.factories;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.entity.utilities.EntityField;
import cz.mg.test.Assert;

public @Test class EntityFieldFactoryTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityFieldFactoryTest.class.getSimpleName() + " ... ");

        EntityFieldFactoryTest test = new EntityFieldFactoryTest();
        test.testCreate();

        System.out.println("OK");
    }

    private void testCreate() {
        Assert.assertExceptionNotThrown(() -> {
            EntityFieldFactory factory = EntityFieldFactory.getInstance();
            EntityField entityField = factory.create(TestEntityClass.class, "Foo", String.class);

            Assert.assertNotNull(entityField);
            Assert.assertEquals(TestEntityClass.class.getMethod("getFoo"), entityField.getGetMethod());
            Assert.assertEquals(TestEntityClass.class.getMethod("setFoo", String.class), entityField.getSetMethod());
        });
    }
}

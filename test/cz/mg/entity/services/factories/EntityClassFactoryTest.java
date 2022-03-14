package cz.mg.entity.services.factories;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.entity.common.TestEntitySubClass;
import cz.mg.entity.utilities.EntityClass;
import cz.mg.test.Assert;

public @Test class EntityClassFactoryTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityClassFactoryTest.class.getSimpleName() + " ... ");

        EntityClassFactoryTest test = new EntityClassFactoryTest();
        test.testCreate();
        test.testCreateSubClass();

        System.out.println("OK");
    }

    private void testCreate() {
        EntityClassFactory factory = EntityClassFactory.getInstance();
        EntityClass entityClass = factory.create(TestEntityClass.class);

        Assert.assertNotNull(entityClass);
        Assert.assertEquals(TestEntityClass.class, entityClass.getClazz());
        Assert.assertEquals(2, entityClass.getFields().count());
        Assert.assertNotNull(entityClass.getFields().get(0));
        Assert.assertNotNull(entityClass.getFields().get(1));
        Assert.assertEquals("Bar", entityClass.getFields().get(0).getName());
        Assert.assertEquals("Foo", entityClass.getFields().get(1).getName());
    }

    private void testCreateSubClass() {
        EntityClassFactory factory = EntityClassFactory.getInstance();
        EntityClass entityClass = factory.create(TestEntitySubClass.class);

        Assert.assertNotNull(entityClass);
        Assert.assertEquals(TestEntitySubClass.class, entityClass.getClazz());
        Assert.assertEquals(3, entityClass.getFields().count());
        Assert.assertNotNull(entityClass.getFields().get(0));
        Assert.assertNotNull(entityClass.getFields().get(1));
        Assert.assertNotNull(entityClass.getFields().get(2));
        Assert.assertEquals("Bar", entityClass.getFields().get(0).getName());
        Assert.assertEquals("Foo", entityClass.getFields().get(1).getName());
        Assert.assertEquals("FooBar", entityClass.getFields().get(2).getName());
    }
}

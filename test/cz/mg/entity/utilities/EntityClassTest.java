package cz.mg.entity.utilities;

import cz.mg.annotations.classes.Test;
import cz.mg.collections.list.List;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.entity.common.TestEntityInterface;
import cz.mg.test.Assert;

public @Test class EntityClassTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityClassTest.class.getSimpleName() + " ... ");

        EntityClassTest test = new EntityClassTest();
        test.testConstructor();
        test.testGetName();
        test.testNewClassInstance();
        test.testNewInterfaceInstance();

        System.out.println("OK");
    }

    private void testConstructor() {
        List<EntityField> entityFields = new List<>();
        EntityClass entityClass = new EntityClass(TestEntityClass.class, entityFields);

        Assert.assertEquals(TestEntityClass.class, entityClass.getClazz());
        Assert.assertEquals(entityFields, entityClass.getFields());
    }

    private void testGetName() {
        EntityClass entityClass = new EntityClass(TestEntityClass.class, new List<>());
        Assert.assertEquals("TestEntityClass", entityClass.getName());
    }

    private void testNewClassInstance() {
        TestEntityClass testEntity = TestEntityClass.entity.newInstance();

        Assert.assertEquals(null, testEntity.getFoo());
        Assert.assertEquals(null, testEntity.getBar());

        testEntity.setFoo(null);
        testEntity.setBar("my bar");

        Assert.assertEquals(null, testEntity.getFoo());
        Assert.assertEquals("my bar", testEntity.getBar());

        testEntity.setFoo("my foo");
        testEntity.setBar("my bar");

        Assert.assertEquals("my foo", testEntity.getFoo());
        Assert.assertEquals("my bar", testEntity.getBar());

        testEntity.setFoo("my foo");
        testEntity.setBar(null);

        Assert.assertEquals("my foo", testEntity.getFoo());
        Assert.assertEquals(null, testEntity.getBar());

        testEntity.setFoo(null);
        testEntity.setBar(null);

        Assert.assertEquals(null, testEntity.getFoo());
        Assert.assertEquals(null, testEntity.getBar());
    }

    private void testNewInterfaceInstance() {
        TestEntityInterface testEntity = TestEntityInterface.entity.newInstance();

        Assert.assertEquals(null, testEntity.getFoo());
        Assert.assertEquals(null, testEntity.getBar());

        testEntity.setFoo(null);
        testEntity.setBar("my bar");

        Assert.assertEquals(null, testEntity.getFoo());
        Assert.assertEquals("my bar", testEntity.getBar());

        testEntity.setFoo("my foo");
        testEntity.setBar("my bar");

        Assert.assertEquals("my foo", testEntity.getFoo());
        Assert.assertEquals("my bar", testEntity.getBar());

        testEntity.setFoo("my foo");
        testEntity.setBar(null);

        Assert.assertEquals("my foo", testEntity.getFoo());
        Assert.assertEquals(null, testEntity.getBar());

        testEntity.setFoo(null);
        testEntity.setBar(null);

        Assert.assertEquals(null, testEntity.getFoo());
        Assert.assertEquals(null, testEntity.getBar());
    }
}

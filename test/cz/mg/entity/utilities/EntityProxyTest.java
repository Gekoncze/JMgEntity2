package cz.mg.entity.utilities;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.TestEntityInterface;
import cz.mg.test.Assert;

import java.lang.reflect.Proxy;
import java.util.Objects;

public @Test class EntityProxyTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityProxyTest.class.getSimpleName() + " ... ");

        EntityProxyTest test = new EntityProxyTest();
        test.testGetAndSet();
        test.testEquals();
        test.testHashCode();
        test.testToString();

        System.out.println("OK");
    }

    private void testGetAndSet() {
        Assert.assertExceptionNotThrown(() -> {
            TestEntityInterface testEntity = TestEntityInterface.entity.newInstance();
            EntityProxy entityProxy = (EntityProxy) Proxy.getInvocationHandler(testEntity);

            testEntity.setFoo("foo1");
            Assert.assertEquals("foo1", entityProxy.invoke(
                testEntity,
                TestEntityInterface.class.getMethod("getFoo"),
                new Object[]{}
            ));

            entityProxy.invoke(
                testEntity,
                TestEntityInterface.class.getMethod("setBar", String.class),
                new Object[]{ "bar1" }
            );
            Assert.assertEquals("bar1", testEntity.getBar());
        });
    }

    private void testEquals() {
        Assert.assertExceptionNotThrown(() -> {
            TestEntityInterface testEntityOne = TestEntityInterface.entity.newInstance();
            EntityProxy entityProxyOne = (EntityProxy) Proxy.getInvocationHandler(testEntityOne);

            TestEntityInterface testEntityTwo = TestEntityInterface.entity.newInstance();
            EntityProxy entityProxyTwo = (EntityProxy) Proxy.getInvocationHandler(testEntityTwo);

            //noinspection EqualsWithItself
            Assert.assertEquals(true, testEntityOne.equals(testEntityOne));
            Assert.assertEquals(false, testEntityOne.equals(testEntityTwo));

            Assert.assertEquals(true, entityProxyOne.invoke(
                testEntityOne,
                Object.class.getMethod("equals", Object.class),
                new Object[]{ testEntityOne }
            ));

            Assert.assertEquals(false, entityProxyOne.invoke(
                testEntityOne,
                Object.class.getMethod("equals", Object.class),
                new Object[]{ testEntityTwo }
            ));
        });
    }

    private void testHashCode() {
        Assert.assertExceptionNotThrown(() -> {
            TestEntityInterface testEntityOne = TestEntityInterface.entity.newInstance();
            EntityProxy entityProxyOne = (EntityProxy) Proxy.getInvocationHandler(testEntityOne);

            TestEntityInterface testEntityTwo = TestEntityInterface.entity.newInstance();
            EntityProxy entityProxyTwo = (EntityProxy) Proxy.getInvocationHandler(testEntityTwo);

            Assert.assertEquals(true, testEntityOne.hashCode() == testEntityOne.hashCode());
            Assert.assertEquals(false, testEntityOne.hashCode() == testEntityTwo.hashCode());

            Assert.assertEquals(testEntityOne.hashCode(), entityProxyOne.invoke(
                testEntityOne,
                Object.class.getMethod("hashCode"),
                new Object[]{}
            ));

            Assert.assertEquals(testEntityTwo.hashCode(), entityProxyTwo.invoke(
                testEntityTwo,
                Object.class.getMethod("hashCode"),
                new Object[]{}
            ));
        });
    }

    private void testToString() {
        Assert.assertExceptionNotThrown(() -> {
            TestEntityInterface testEntityOne = TestEntityInterface.entity.newInstance();
            EntityProxy entityProxyOne = (EntityProxy) Proxy.getInvocationHandler(testEntityOne);

            TestEntityInterface testEntityTwo = TestEntityInterface.entity.newInstance();
            EntityProxy entityProxyTwo = (EntityProxy) Proxy.getInvocationHandler(testEntityTwo);

            Assert.assertEquals(true, Objects.equals(testEntityOne.toString(), testEntityOne.toString()));
            Assert.assertEquals(false, Objects.equals(testEntityOne.toString(), testEntityTwo.toString()));

            Assert.assertEquals(testEntityOne.toString(), entityProxyOne.invoke(
                testEntityOne,
                Object.class.getMethod("toString"),
                new Object[]{}
            ));

            Assert.assertEquals(testEntityTwo.toString(), entityProxyTwo.invoke(
                testEntityTwo,
                Object.class.getMethod("toString"),
                new Object[]{}
            ));
        });
    }
}

package cz.mg.entity.mapper.structure;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.common.TestEntityClass;
import cz.mg.entity.mapper.Mapper;
import cz.mg.entity.mapper.value.StringObjectMapper;
import cz.mg.entity.services.factories.EntityClassFactory;
import cz.mg.test.Assert;

public @Test class EntityObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityObjectMapperTest.class.getSimpleName() + " ... ");

        EntityObjectMapperTest test = new EntityObjectMapperTest();
        test.testNullMapping();
        test.testMapping();

        System.out.println("OK");
    }

    private void testNullMapping() {
        Mapper<TestEntityClass> mapper = new Mapper<>(new List<>(
            new EntityObjectMapper(EntityClassFactory.getInstance().create(TestEntityClass.class)),
            new StringObjectMapper()
        ));
        Assert.assertNull(mapper.unmap(mapper.map(null)));
    }

    private void testMapping() {
        test(new TestEntityClass(null, null));
        test(new TestEntityClass("abc", null));
        test(new TestEntityClass(null, "def"));
        test(new TestEntityClass("abc", "def"));
    }

    private void test(@Mandatory TestEntityClass entity) {
        Mapper<TestEntityClass> mapper = new Mapper<>(new List<>(
            new EntityObjectMapper(EntityClassFactory.getInstance().create(TestEntityClass.class)),
            new StringObjectMapper()
        ));
        TestEntityClass after = mapper.unmap(mapper.map(entity));
        Assert.assertNotSame(entity, after);
        Assert.assertNotNull(after);
        Assert.assertEquals(entity.getFoo(), after.getFoo());
        Assert.assertEquals(entity.getBar(), after.getBar());
    }
}

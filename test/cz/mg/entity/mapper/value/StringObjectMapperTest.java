package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class StringObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + StringObjectMapperTest.class.getSimpleName() + " ... ");

        StringObjectMapperTest test = new StringObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        test("");
        test(" ");
        test("a");
        test("abc");
        test(" abc");
        test("abc ");
        test(" abc ");
        test(" a b c ");
        test("1");
        test("123");
        test("-123");
        test("+123!");
        test(";,.?!5$#@^&{}[]{}");
        test("\t\n\r");
    }

    private void test(@Optional String value) {
        Mapper<String> mapper = new Mapper<>(new List<>(new StringObjectMapper()));
        String after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

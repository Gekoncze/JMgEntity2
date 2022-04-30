package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Mapper;
import cz.mg.test.Assert;

public @Test class ByteObjectMapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + ByteObjectMapperTest.class.getSimpleName() + " ... ");

        ByteObjectMapperTest test = new ByteObjectMapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        test(null);
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            test((byte)i);
        }
        test(Byte.MAX_VALUE);
    }

    private void test(@Optional Byte value) {
        Mapper<Byte> mapper = new Mapper<>(new List<>(new ByteObjectMapper()));
        Byte after = mapper.unmap(mapper.map(value));
        Assert.assertEquals(value, after);
    }
}

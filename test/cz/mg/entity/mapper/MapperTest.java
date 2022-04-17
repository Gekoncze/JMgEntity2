package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Test;

public @Test class MapperTest {
    public static void main(String[] args) {
        System.out.print("Running " + MapperTest.class.getSimpleName() + " ... ");

        MapperTest test = new MapperTest();
        test.testMapping();

        System.out.println("OK");
    }

    private void testMapping() {
        // TODO
    }
}

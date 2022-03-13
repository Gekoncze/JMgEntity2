package cz.mg.entity;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.utilities.EntityClassTest;
import cz.mg.entity.utilities.EntityFieldTest;
import cz.mg.entity.utilities.EntityProxyTest;

public @Test class AllTests {
    public static void main(String[] args) {
        EntityFieldTest.main(args);
        EntityClassTest.main(args);
        EntityProxyTest.main(args);
    }
}

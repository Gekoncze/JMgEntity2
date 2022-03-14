package cz.mg.entity;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.services.factories.EntityClassFactoryTest;
import cz.mg.entity.services.factories.EntityFieldFactoryTest;
import cz.mg.entity.services.validators.EntityClassValidatorTest;
import cz.mg.entity.services.validators.EntityInterfaceValidatorTest;
import cz.mg.entity.utilities.EntityClassTest;
import cz.mg.entity.utilities.EntityFieldTest;
import cz.mg.entity.utilities.EntityProxyTest;

public @Test class AllTests {
    public static void main(String[] args) {
        EntityClassTest.main(args);
        EntityFieldTest.main(args);
        EntityProxyTest.main(args);

        EntityClassFactoryTest.main(args);
        EntityFieldFactoryTest.main(args);

        EntityClassValidatorTest.main(args);
        EntityInterfaceValidatorTest.main(args);
    }
}

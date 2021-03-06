package cz.mg.entity;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.mapper.MapperFactoryTest;
import cz.mg.entity.mapper.MapperTest;
import cz.mg.entity.mapper.collection.ListObjectMapperTest;
import cz.mg.entity.mapper.structure.EntityObjectMapperTest;
import cz.mg.entity.mapper.value.*;
import cz.mg.entity.services.factories.EntityClassFactoryTest;
import cz.mg.entity.services.factories.EntityFieldFactoryTest;
import cz.mg.entity.services.validators.EntityClassValidatorTest;
import cz.mg.entity.services.validators.EntityFieldValidatorTest;
import cz.mg.entity.services.validators.EntityInterfaceValidatorTest;
import cz.mg.entity.storage.EncoderTest;
import cz.mg.entity.storage.StorageTest;
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
        EntityFieldValidatorTest.main(args);
        EntityInterfaceValidatorTest.main(args);

        ListObjectMapperTest.main(args);
        BooleanObjectMapperTest.main(args);
        ByteObjectMapperTest.main(args);
        DoubleObjectMapperTest.main(args);
        EnumObjectMapperTest.main(args);
        FloatObjectMapperTest.main(args);
        IntegerObjectMapperTest.main(args);
        LongObjectMapperTest.main(args);
        StringObjectMapperTest.main(args);
        EntityObjectMapperTest.main(args);
        MapperFactoryTest.main(args);
        MapperTest.main(args);

        EncoderTest.main(args);
        StorageTest.main(args);
    }
}

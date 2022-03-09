package cz.mg.entity.common;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.services.factories.EntityClassFactory;
import cz.mg.entity.utilities.EntityClass;

public @Entity interface TestEntityInterface {
    EntityClass entity = EntityClassFactory.getInstance().create(TestEntityInterface.class);

    @Value
    String getFoo();
    void setFoo(String foo);

    @Value
    String getBar();
    void setBar(String bar);
}

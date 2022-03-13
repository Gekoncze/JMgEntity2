package cz.mg.entity.common;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.services.factories.EntityClassFactory;
import cz.mg.entity.utilities.EntityClass;

public @Entity interface TestEntitySubInterface extends TestEntityInterface {
    EntityClass entity = EntityClassFactory.getInstance().create(TestEntitySubInterface.class);

    @Value
    String getFooBar();
    void setFooBar(String fooBar);
}

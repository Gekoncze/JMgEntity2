package cz.mg.entity.common;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.services.factories.EntityClassFactory;
import cz.mg.entity.utilities.EntityClass;

public @Entity class TestEntitySubClass extends TestEntityClass {
    public static EntityClass entity = EntityClassFactory.getInstance().create(TestEntitySubClass.class);

    private @Value String fooBar;

    public TestEntitySubClass() {
    }

    public String getFooBar() {
        return fooBar;
    }

    public void setFooBar(String fooBar) {
        this.fooBar = fooBar;
    }
}

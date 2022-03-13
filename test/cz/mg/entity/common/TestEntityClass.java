package cz.mg.entity.common;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.services.factories.EntityClassFactory;
import cz.mg.entity.utilities.EntityClass;

public @Entity class TestEntityClass {
    public static EntityClass entity = EntityClassFactory.getInstance().create(TestEntityClass.class);

    private @Value String foo;
    private @Value String bar;

    public TestEntityClass() {
    }

    @Value
    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Value
    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}

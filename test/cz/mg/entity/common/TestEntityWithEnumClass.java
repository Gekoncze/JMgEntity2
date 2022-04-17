package cz.mg.entity.common;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.services.factories.EntityClassFactory;
import cz.mg.entity.utilities.EntityClass;

public @Entity class TestEntityWithEnumClass {
    public static EntityClass entity = EntityClassFactory.getInstance().create(TestEntityWithEnumClass.class);

    private @Value TestEnum foo;
    private @Value String bar;

    public TestEntityWithEnumClass() {
    }

    public TestEntityWithEnumClass(TestEnum foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    @Value
    public TestEnum getFoo() {
        return foo;
    }

    public void setFoo(TestEnum foo) {
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

package cz.mg.entity.common.invalid;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;

public @Entity class TestEntityClassWithoutPublicConstructor {
    private @Value String foo;
    private @Value String bar;

    private TestEntityClassWithoutPublicConstructor() {
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

package cz.mg.entity.common;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;

public @Entity class TestEntityClass {
    private String foo;
    private String bar;

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

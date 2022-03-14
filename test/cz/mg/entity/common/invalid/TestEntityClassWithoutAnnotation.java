package cz.mg.entity.common.invalid;

import cz.mg.annotations.storage.Value;

public class TestEntityClassWithoutAnnotation {
    private @Value String foo;
    private @Value String bar;

    public TestEntityClassWithoutAnnotation() {
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

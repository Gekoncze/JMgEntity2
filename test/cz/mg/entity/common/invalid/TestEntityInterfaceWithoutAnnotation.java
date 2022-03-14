package cz.mg.entity.common.invalid;

import cz.mg.annotations.storage.Value;

public interface TestEntityInterfaceWithoutAnnotation {
    @Value
    String getFoo();
    void setFoo(String foo);

    @Value
    String getBar();
    void setBar(String bar);
}

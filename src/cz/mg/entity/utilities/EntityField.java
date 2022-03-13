package cz.mg.entity.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;

import java.lang.reflect.Method;

public @Utility class EntityField {
    private final @Mandatory @Link Method getMethod;
    private final @Mandatory @Link Method setMethod;

    public EntityField(@Mandatory Method getMethod, @Mandatory Method setMethod) {
        this.getMethod = getMethod;
        this.setMethod = setMethod;
    }

    public @Mandatory Method getGetMethod() {
        return getMethod;
    }

    public @Mandatory Method getSetMethod() {
        return setMethod;
    }

    public @Mandatory String getName() {
        return getMethod.getName().substring(3);
    }

    public @Optional Object get(@Mandatory Object parent) {
        try {
            return getMethod.invoke(parent);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public void set(@Mandatory Object parent, @Optional Object value) {
        try {
            setMethod.invoke(parent, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

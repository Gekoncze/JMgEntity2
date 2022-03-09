package cz.mg.entity.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.collections.map.Map;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

public @Utility class EntityProxy implements InvocationHandler {
    private final @Mandatory @Link EntityClass entityClass;
    private final Map<String, Object> map;

    public EntityProxy(@Mandatory EntityClass entityClass) {
        this.entityClass = entityClass;
        this.map = new Map<>(entityClass.getFields().count() * 2);
    }

    @Override
    public @Optional Object invoke(@Mandatory Object proxy, @Mandatory Method method, Object[] args) {
        if(method.getName().startsWith("get")) {
            return map.get(method.getName().substring(3));
        }

        if(method.getName().startsWith("set")) {
            return map.get(method.getName().substring(3));
        }

        if(method.getName().equals("equals")) {
            return proxy == args[0];
        }

        if(method.getName().equals("hashCode")) {
            return Objects.hashCode(this);
        }

        throw new UnsupportedOperationException("Unsupported method call " + method.getName() + " for class " + entityClass.getName());
    }
}

package cz.mg.entity.services.factories;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.utilities.EntityField;
import cz.mg.entity.services.validators.EntityFieldValidator;

import java.lang.reflect.Method;

public @Service class EntityFieldFactory {
    private static EntityFieldFactory instance;

    public static EntityFieldFactory getInstance() {
        if(instance == null) {
            instance = new EntityFieldFactory();
            instance.entityFieldValidator = EntityFieldValidator.getInstance();
        }
        return instance;
    }

    private EntityFieldValidator entityFieldValidator;

    private EntityFieldFactory() {
    }

    public @Mandatory EntityField create(@Mandatory Class<?> clazz, @Mandatory String fieldName) {
        try {
            Method getter = clazz.getMethod("get" + fieldName);
            Method setter = clazz.getMethod("set" + fieldName);
            entityFieldValidator.validateGetter(getter);
            entityFieldValidator.validateSetter(setter);
            return new EntityField(getter, setter);
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}

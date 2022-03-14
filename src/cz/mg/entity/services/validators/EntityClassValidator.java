package cz.mg.entity.services.validators;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public @Service class EntityClassValidator {
    private static EntityClassValidator instance;

    public static EntityClassValidator getInstance() {
        if (instance == null) {
            instance = new EntityClassValidator();
        }
        return instance;
    }

    private EntityClassValidator() {
    }

    public void validate(@Mandatory Class<?> clazz) {
        validateAnnotation(clazz);
        validateConstructor(clazz);
    }

    private void validateAnnotation(@Mandatory Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Missing entity annotation for class " + clazz.getSimpleName() + ".");
        }
    }

    private void validateConstructor(@Mandatory Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                if (Modifier.isPublic(constructor.getModifiers())) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Could not find public parameter-less constructor.");
    }
}

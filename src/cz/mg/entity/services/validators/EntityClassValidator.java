package cz.mg.entity.services.validators;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;

public @Service class EntityClassValidator {
    private static EntityClassValidator instance;

    public static EntityClassValidator getInstance() {
        if(instance == null) {
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

    private void validateConstructor(@Mandatory Class<?> clazz) {
        try {
            clazz.getConstructor();
        } catch(ReflectiveOperationException e) {
            throw new IllegalArgumentException("Could not find parameter-less constructor.");
        }
    }

    private void validateAnnotation(@Mandatory Class<?> clazz) {
        if(!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Missing entity annotation for class " + clazz.getSimpleName() + ".");
        }
    }
}

package cz.mg.entity.services.validators;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;

public @Service class EntityInterfaceValidator {
    private static EntityInterfaceValidator instance;

    public static EntityInterfaceValidator getInstance() {
        if(instance == null) {
            instance = new EntityInterfaceValidator();
        }

        return instance;
    }

    private EntityInterfaceValidator() {
    }

    public void validate(@Mandatory Class<?> entityClass) {
        validateAnnotation(entityClass);
    }

    private void validateAnnotation(@Mandatory Class<?> entityClass) {
        if(!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Missing entity annotation for class " + entityClass.getSimpleName() + ".");
        }
    }
}

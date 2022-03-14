package cz.mg.entity.services.validators;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;

import java.lang.reflect.Method;

public @Service class EntityFieldValidator {
    private static EntityFieldValidator instance;

    public static EntityFieldValidator getInstance() {
        if (instance == null) {
            instance = new EntityFieldValidator();
        }
        return instance;
    }

    private EntityFieldValidator() {
    }

    public void validateGetter(@Mandatory Method method) {
        boolean isNameValid = method.getName().startsWith("get");
        if (!isNameValid) {
            throw new IllegalArgumentException("Invalid getter name " + method.getName() + ".");
        }

        if (method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Getter must have no parameters.");
        }

        if (method.getReturnType().equals(Void.TYPE)) {
            throw new IllegalArgumentException("Getter must have return type.");
        }

        if (method.getReturnType().isPrimitive()) {
            throw new IllegalArgumentException("Getter return type must not be primitive.");
        }

        boolean hasLinkAnnotation = method.isAnnotationPresent(Link.class);
        boolean hasPartAnnotation = method.isAnnotationPresent(Part.class);
        boolean hasValueAnnotation = method.isAnnotationPresent(Value.class);

        if (!(hasLinkAnnotation || hasPartAnnotation || hasValueAnnotation)) {
            throw new IllegalArgumentException("Missing field annotation for method " + method.getName() + ".");
        }
    }

    public void validateSetter(@Mandatory Method method) {
        boolean isNameValid = method.getName().startsWith("set");
        if (!isNameValid) {
            throw new IllegalArgumentException("Invalid setter name " + method.getName() + ".");
        }

        if (method.getParameterCount() != 1) {
            throw new IllegalArgumentException("Setter must have exactly one parameter.");
        }

        if (method.getParameterTypes()[0].isPrimitive()) {
            throw new IllegalArgumentException("Setter parameter type must not be primitive.");
        }

        if (!method.getReturnType().equals(Void.TYPE)) {
            throw new IllegalArgumentException("Setter must have no return type.");
        }
    }
}

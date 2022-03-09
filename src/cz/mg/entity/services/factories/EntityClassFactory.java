package cz.mg.entity.services.factories;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.services.validators.EntityInterfaceValidator;
import cz.mg.entity.utilities.EntityClass;
import cz.mg.entity.utilities.EntityField;
import cz.mg.entity.services.validators.EntityClassValidator;

import java.lang.reflect.Method;

public @Service class EntityClassFactory {
    private static EntityClassFactory instance;

    public static EntityClassFactory getInstance() {
        if(instance == null) {
            instance = new EntityClassFactory();
            instance.entityFieldFactory = EntityFieldFactory.getInstance();
            instance.entityClassValidator = EntityClassValidator.getInstance();
            instance.entityInterfaceValidator = EntityInterfaceValidator.getInstance();
        }
        return instance;
    }

    private EntityFieldFactory entityFieldFactory;
    private EntityClassValidator entityClassValidator;
    private EntityInterfaceValidator entityInterfaceValidator;

    private EntityClassFactory() {
    }

    public @Mandatory EntityClass create(@Mandatory Class<?> clazz) {
        if(clazz.isInterface()) {
            entityInterfaceValidator.validate(clazz);
        } else {
            entityClassValidator.validate(clazz);
        }

        List<EntityField> fields = new List<>();
        for(Method method : clazz.getMethods()) {
            boolean isGetter = method.getName().startsWith("get");
            boolean hasLinkAnnotation = method.isAnnotationPresent(Link.class);
            boolean hasPartAnnotation = method.isAnnotationPresent(Part.class);
            boolean hasValueAnnotation = method.isAnnotationPresent(Value.class);
            boolean hasAnnotation = hasLinkAnnotation || hasPartAnnotation || hasValueAnnotation;
            if(isGetter && hasAnnotation){
                fields.addLast(
                    entityFieldFactory.create(
                        clazz,
                        method.getName().substring(3),
                        method.getReturnType()
                    )
                );
            }
        }
        return new EntityClass(clazz, fields);
    }
}

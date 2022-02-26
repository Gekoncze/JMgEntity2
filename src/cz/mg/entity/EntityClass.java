package cz.mg.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;

@SuppressWarnings("rawtypes")
public @Utility class EntityClass {
    private final @Mandatory @Link Class clazz;
    private final @Mandatory @Part List<EntityField> fields;

    public EntityClass(@Mandatory Class clazz, @Mandatory List<EntityField> fields) {
        this.clazz = clazz;
        this.fields = fields;
    }

    public @Mandatory Class getClazz() {
        return clazz;
    }

    public @Mandatory List<EntityField> getFields() {
        return fields;
    }

    public @Mandatory String getName() {
        return clazz.getSimpleName();
    }

    public @Mandatory Object newInstance() {
        if(clazz.isInterface()) {
            return new EntityProxy(this);
        } else {
            try {
                //noinspection unchecked
                return clazz.getConstructor().newInstance();
            } catch(ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.entity.utilities.EntityClass;
import cz.mg.entity.utilities.EntityField;


public @Utility class EntityObjectMapper implements ObjectMapper<Object> {
    private final @Mandatory @Part EntityClass entityClass;

    public EntityObjectMapper(@Mandatory EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public @Mandatory String getName() {
        return entityClass.getName();
    }

    @Override
    public @Mandatory Object create(@Optional String value) {
        return entityClass.newInstance();
    }

    @Override
    public @Optional String getValue(@Mandatory Object object) {
        return null;
    }

    @Override
    public @Mandatory List<Object> getFields(@Mandatory Object object) {
        List<Object> fieldValues = new List<>();
        for (EntityField field : entityClass.getFields()) {
            fieldValues.addLast(field.get(object));
        }
        return fieldValues;
    }

    @Override
    public void setFields(@Mandatory Object object, @Mandatory List<Object> fieldValues) {
        int i = 0;
        for (Object fieldValue : fieldValues) {
            entityClass.getFields().get(i).set(object, fieldValue);
            i++;
        }
    }
}

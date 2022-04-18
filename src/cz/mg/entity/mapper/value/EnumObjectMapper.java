package cz.mg.entity.mapper.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;

import java.lang.reflect.Method;


@SuppressWarnings({"rawtypes", "unchecked"})
public @Utility class EnumObjectMapper<T extends Enum> implements ValueObjectMapper<T> {
    private final Class<T> clazz;

    public EnumObjectMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public @Mandatory String getName() {
        return clazz.getSimpleName();
    }

    @Override
    public @Mandatory T create(@Mandatory String value) {
        try {
            Method factory = clazz.getMethod("valueOf", String.class);
            return (T) factory.invoke(null, value);
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Mandatory String getValue(@Mandatory T object) {
        return object.name();
    }
}

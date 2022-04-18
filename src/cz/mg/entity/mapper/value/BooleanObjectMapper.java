package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;


public class BooleanObjectMapper implements ValueObjectMapper<Boolean> {
    @Override
    public @Mandatory String getName() {
        return Boolean.class.getSimpleName();
    }

    @Override
    public @Mandatory Boolean create(@Mandatory String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public @Mandatory String getValue(@Mandatory Boolean object) {
        return Boolean.toString(object);
    }
}

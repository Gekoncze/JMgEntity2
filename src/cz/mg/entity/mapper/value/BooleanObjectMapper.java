package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import java.util.Objects;


public class BooleanObjectMapper implements ValueObjectMapper<Boolean> {
    @Override
    public @Mandatory String getName() {
        return Boolean.class.getSimpleName();
    }

    @Override
    public @Mandatory Boolean create(@Optional String value) {
        return Boolean.parseBoolean(Objects.requireNonNull(value));
    }

    @Override
    public @Optional String getValue(@Mandatory Boolean object) {
        return Boolean.toString(object);
    }
}

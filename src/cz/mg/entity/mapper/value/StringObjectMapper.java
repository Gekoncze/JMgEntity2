package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import java.util.Objects;


public class StringObjectMapper implements ValueObjectMapper<String> {
    @Override
    public @Mandatory String getName() {
        return String.class.getSimpleName();
    }

    @Override
    public @Mandatory String create(@Optional String value) {
        return Objects.requireNonNull(value);
    }

    @Override
    public @Optional String getValue(@Mandatory String object) {
        return object;
    }
}

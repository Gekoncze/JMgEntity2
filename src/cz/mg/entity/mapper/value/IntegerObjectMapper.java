package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import java.util.Objects;


public class IntegerObjectMapper implements ValueObjectMapper<Integer> {
    @Override
    public @Mandatory String getName() {
        return Integer.class.getSimpleName();
    }

    @Override
    public @Mandatory Integer create(@Optional String value) {
        return Integer.parseInt(Objects.requireNonNull(value));
    }

    @Override
    public @Optional String getValue(@Mandatory Integer object) {
        return Integer.toString(object);
    }
}

package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;


public class IntegerObjectMapper implements ValueObjectMapper<Integer> {
    @Override
    public @Mandatory String getName() {
        return Integer.class.getSimpleName();
    }

    @Override
    public @Mandatory Integer create(@Mandatory String value) {
        return Integer.parseInt(value);
    }

    @Override
    public @Mandatory String getValue(@Mandatory Integer object) {
        return Integer.toString(object);
    }
}

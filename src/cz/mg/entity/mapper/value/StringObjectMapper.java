package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;


public class StringObjectMapper implements ValueObjectMapper<String> {
    @Override
    public @Mandatory String getName() {
        return String.class.getSimpleName();
    }

    @Override
    public @Mandatory String create(@Mandatory String value) {
        return value;
    }

    @Override
    public @Mandatory String getValue(@Mandatory String object) {
        return object;
    }
}

package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;


public class LongObjectMapper implements ValueObjectMapper<Long> {
    @Override
    public @Mandatory String getName() {
        return Long.class.getSimpleName();
    }

    @Override
    public @Mandatory Long create(@Mandatory String value) {
        return Long.parseLong(value);
    }

    @Override
    public @Mandatory String getValue(@Mandatory Long object) {
        return Long.toString(object);
    }
}

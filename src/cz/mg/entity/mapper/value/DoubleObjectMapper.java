package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;


public class DoubleObjectMapper implements ValueObjectMapper<Double> {
    @Override
    public @Mandatory String getName() {
        return Double.class.getSimpleName();
    }

    @Override
    public @Mandatory Double create(@Mandatory String value) {
        return Double.parseDouble(value);
    }

    @Override
    public @Mandatory String getValue(@Mandatory Double object) {
        return Double.toString(object);
    }
}

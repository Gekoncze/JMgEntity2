package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;


public class FloatObjectMapper implements ValueObjectMapper<Float> {
    @Override
    public @Mandatory String getName() {
        return Float.class.getSimpleName();
    }

    @Override
    public @Mandatory Float create(@Mandatory String value) {
        return Float.parseFloat(value);
    }

    @Override
    public @Mandatory String getValue(@Mandatory Float object) {
        return Float.toString(object);
    }
}

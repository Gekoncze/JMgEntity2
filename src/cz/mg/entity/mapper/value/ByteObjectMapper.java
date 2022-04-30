package cz.mg.entity.mapper.value;

import cz.mg.annotations.requirement.Mandatory;


public class ByteObjectMapper implements ValueObjectMapper<Byte> {
    @Override
    public @Mandatory String getName() {
        return Byte.class.getSimpleName();
    }

    @Override
    public @Mandatory Byte create(@Mandatory String value) {
        return Byte.parseByte(value);
    }

    @Override
    public @Mandatory String getValue(@Mandatory Byte object) {
        return Byte.toString(object);
    }
}

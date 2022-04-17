package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public @Service class Converter {
    private static Converter instance;

    public static Converter getInstance() {
        if (instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    public byte[] integerToBytes(@Optional Integer value) {
        if (value != null) {
            return ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
        } else {
            return null;
        }
    }

    public byte[] stringToBytes(@Optional String value) {
        if (value != null) {
            return value.getBytes(StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }
}

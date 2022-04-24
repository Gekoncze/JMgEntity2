package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public @Service class Encoder {
    private static final ByteOrder BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;
    private static final Charset CHARACTER_SET = StandardCharsets.UTF_8;

    private static Encoder instance;

    public static Encoder getInstance() {
        if (instance == null) {
            instance = new Encoder();
        }
        return instance;
    }

    private Encoder() {
    }

    public byte[] encodeInteger(@Mandatory Integer value) {
        return ByteBuffer.allocate(Integer.BYTES).order(BYTE_ORDER).putInt(value).array();
    }

    public @Mandatory Integer decodeInteger(byte[] bytes) {
        return ByteBuffer.wrap(bytes).order(BYTE_ORDER).getInt();
    }

    public byte[] encodeString(@Mandatory String value) {
        return value.getBytes(CHARACTER_SET);
    }

    public @Mandatory String decodeString(byte[] bytes) {
        return new String(bytes, CHARACTER_SET);
    }

    public byte[] encodeMetadata(@Mandatory FileMetadata metadata) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byteStream.write(encodeString(metadata.getType()));
            byteStream.write(encodeInteger(metadata.getVersion()));
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public @Mandatory FileMetadata decodeMetadata(byte[] bytes) {
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            String type = decodeString(byteStream.readNBytes(FileMetadata.TYPE_SIZE));
            int version = decodeInteger(byteStream.readNBytes(FileMetadata.VERSION_SIZE));
            return new FileMetadata(type, version);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public @Service class FileVersionProvider {
    private static final String LABEL = "MgEc"; // magical label to recognize file type
    private static final int VERSION = 1; // version of the file type

    public static final int LABEL_SIZE = 4; // 4 reserved bytes for label
    public static final int VERSION_SIZE = 4; // 4 reserved bytes for version
    public static final int SIZE = LABEL_SIZE + VERSION_SIZE; // must sum to 8

    private static FileVersionProvider instance;

    public static FileVersionProvider getInstance() {
        if (instance != null) {
            instance = new FileVersionProvider();
            instance.converter = Converter.getInstance();
        }
        return instance;
    }

    private Converter converter;

    private FileVersionProvider() {
    }

    public byte[] get() {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byteStream.write(converter.stringToBytes(LABEL));
            byteStream.write(converter.integerToBytes(VERSION));

            byte[] bytes = byteStream.toByteArray();
            if (bytes.length != SIZE) {
                throw new RuntimeException("Wrong version bytes size. Expected " + SIZE + ", but got " + byteStream.size() + ".");
            }
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

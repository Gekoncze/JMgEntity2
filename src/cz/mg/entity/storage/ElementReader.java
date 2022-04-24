package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public @Service class ElementReader {
    private static ElementReader instance;

    public static ElementReader getInstance() {
        if (instance == null) {
            instance = new ElementReader();
            instance.encoder = Encoder.getInstance();
        }
        return instance;
    }

    private Encoder encoder;

    private ElementReader() {
    }

    public @Mandatory List<Element> read(@Mandatory String path) {
        try (FileInputStream stream = new FileInputStream(path)) {
            return read(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public @Mandatory List<Element> read(@Mandatory InputStream stream) {
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(stream.readAllBytes());
            checkMetadata(readMetadata(byteStream));
            return readElements(byteStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @Mandatory FileMetadata readMetadata(@Mandatory ByteArrayInputStream byteStream) throws IOException {
        return encoder.decodeMetadata(byteStream.readNBytes(FileMetadata.SIZE));
    }

    private void checkMetadata(@Mandatory FileMetadata metadata) {
        if (!Objects.equals(metadata.getType(), FileMetadata.CURRENT_TYPE)) {
            throw new IllegalArgumentException("Wrong file type. Expected '" + FileMetadata.CURRENT_TYPE + "'.");
        }

        if (!Objects.equals(metadata.getVersion(), FileMetadata.CURRENT_VERSION)) {
            throw new IllegalArgumentException("Wrong file version. Expected '" + FileMetadata.CURRENT_VERSION + "', but got '" + metadata.getVersion() + "'.");
        }
    }

    private @Mandatory List<Element> readElements(@Mandatory ByteArrayInputStream byteStream) throws IOException {
        int elementCount = encoder.decodeInteger(byteStream.readNBytes(Integer.BYTES));
        List<Element> elements = new List<>();
        for (int i = 0; i < elementCount; i++) {
            elements.addLast(readElement(byteStream));
        }
        return elements;
    }

    private @Mandatory Element readElement(@Mandatory ByteArrayInputStream byteStream) throws IOException {
        int nameLength = encoder.decodeInteger(byteStream.readNBytes(Integer.BYTES));
        String name = encoder.decodeString(byteStream.readNBytes(nameLength));

        int valueLength = encoder.decodeInteger(byteStream.readNBytes(Integer.BYTES));
        String value = encoder.decodeString(byteStream.readNBytes(valueLength));

        return new Element(name, value, readFields(byteStream));
    }

    private @Mandatory List<Integer> readFields(@Mandatory ByteArrayInputStream byteStream) throws IOException {
        int count = encoder.decodeInteger(byteStream.readNBytes(Integer.BYTES));
        List<Integer> fields = new List<>();
        for (int i = 0; i < count; i++) {
            fields.addLast(encoder.decodeInteger(byteStream.readNBytes(Integer.BYTES)));
        }
        return fields;
    }
}

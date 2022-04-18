package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public @Service class ElementWriter {
    private static ElementWriter instance;

    public static ElementWriter getInstance() {
        if (instance == null) {
            instance = new ElementWriter();
            instance.encoder = Encoder.getInstance();
        }
        return instance;
    }

    private Encoder encoder;

    private ElementWriter() {
    }

    public void write(@Mandatory List<Element> elements, @Mandatory String path) {
        try (FileOutputStream stream = new FileOutputStream(path)) {
            write(elements, stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(@Mandatory List<Element> elements, @Mandatory OutputStream stream) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            writeMetadata(byteStream);
            writeElements(byteStream, elements);
            byteStream.writeTo(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeMetadata(@Mandatory ByteArrayOutputStream byteStream) throws IOException {
        byteStream.write(encoder.encodeMetadata(new FileMetadata(
            FileMetadata.CURRENT_TYPE,
            FileMetadata.CURRENT_VERSION
        )));
    }

    private void writeElements(
        @Mandatory ByteArrayOutputStream byteStream,
        @Mandatory List<Element> elements
    ) throws IOException {
        byteStream.write(encoder.encodeInteger(elements.count()));
        for (Element element : elements) {
            writeElement(byteStream, element);
        }
    }

    private void writeElement(
        @Mandatory ByteArrayOutputStream byteStream,
        @Mandatory Element element
    ) throws IOException {
        byte[] name = encoder.encodeString(element.getName());
        byteStream.write(encoder.encodeInteger(name.length));
        byteStream.write(name);

        byte[] value = encoder.encodeString(element.getValue());
        byteStream.write(encoder.encodeInteger(value.length));
        byteStream.write(value);

        writeFields(byteStream, element.getFields());
    }

    private void writeFields(
        @Mandatory ByteArrayOutputStream byteStream,
        @Mandatory List<Integer> fields
    ) throws IOException {
        byteStream.write(encoder.encodeInteger(fields.count()));
        for (Integer field : fields) {
            byteStream.write(encoder.encodeInteger(field));
        }
    }
}

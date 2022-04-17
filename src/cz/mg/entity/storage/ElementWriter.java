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
            instance.converter = Converter.getInstance();
            instance.fileVersionProvider = FileVersionProvider.getInstance();
        }
        return instance;
    }

    private Converter converter;
    private FileVersionProvider fileVersionProvider;

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
            for (Element element : elements) {
                writeElement(byteStream, element);
            }
            byteStream.writeTo(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeElement(@Mandatory ByteArrayOutputStream byteStream, @Mandatory Element element) {
        throw new UnsupportedOperationException("Coming soon!"); // TODO
    }
}

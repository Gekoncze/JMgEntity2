package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public @Service class ElementReader {
    private static ElementReader instance;

    public static ElementReader getInstance() {
        if (instance == null) {
            instance = new ElementReader();
            instance.converter = Converter.getInstance();
            instance.fileVersionProvider = FileVersionProvider.getInstance();
        }
        return instance;
    }

    private Converter converter;
    private FileVersionProvider fileVersionProvider;

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
        throw new UnsupportedOperationException("Coming soon!"); // TODO
    }
}

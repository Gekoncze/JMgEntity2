package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;


@SuppressWarnings({"rawtypes", "unchecked"})
public @Utility class Mapper<T> {
    private final @Mandatory @Part List<ObjectMapper> objectMappers;

    public Mapper(@Mandatory List<ObjectMapper> objectMappers) {
        this.objectMappers = objectMappers;
    }

    public @Mandatory List<Element> map(@Optional T object) {
        List<Element> elements = new List<>();
        Map<Object, Integer> cache = new Map<>(100);
        map(elements, cache, object);
        return elements;
    }

    private @Optional Integer map(
        @Mandatory List<Element> elements,
        @Mandatory Map<Object, Integer> cache,
        @Optional Object object
    ) {
        if (object == null) {
            return null;
        }

        if (cache.getOptional(object) != null) {
            return cache.get(object);
        }

        int id = elements.count();

        ObjectMapper objectMapper = find(object.getClass().getSimpleName());
        Element element = new Element(
            objectMapper.getName(),
            objectMapper.getValue(object),
            new List<>()
        );

        elements.addLast(element);
        cache.set(object, id);

        for (Object field : objectMapper.getFields(object)) {
            element.getFields().addLast(
                map(elements, cache, field)
            );
        }

        return id;
    }

    public @Optional T unmap(@Mandatory List<Element> elements) {
        Map<Integer, Object> cache = new Map<>(100);
        return (T) unmap(elements, cache, 0);
    }

    private @Optional Object unmap(
        @Mandatory List<Element> elements,
        @Mandatory Map<Integer, Object> cache,
        @Optional Integer id
    ) {
        if (id == null) {
            return null;
        }

        if (cache.getOptional(id) != null) {
            return cache.get(id);
        }

        Element element = elements.get(id);
        if (element == null) {
            throw new NullPointerException("Could not find entity with id " + id + ".");
        }

        ObjectMapper objectMapper = find(element.getName());
        Object object = objectMapper.create(element.getValue());

        cache.set(id, object);

        List<Object> fields = new List<>();
        for (Integer field : element.getFields()) {
            fields.addLast(
                unmap(elements, cache, field)
            );
        }
        objectMapper.setFields(object, fields);

        return object;
    }

    private @Mandatory ObjectMapper find(@Mandatory String name) {
        for (ObjectMapper objectMapper : objectMappers) {
            if (objectMapper.getName().equals(name)) {
                return objectMapper;
            }
        }

        throw new IllegalArgumentException("Missing object mapper for '" + name + "'.");
    }
}

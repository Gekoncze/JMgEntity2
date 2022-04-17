package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.collection.ListObjectMapper;
import cz.mg.entity.mapper.structure.EntityObjectMapper;
import cz.mg.entity.mapper.value.BooleanObjectMapper;
import cz.mg.entity.mapper.value.EnumObjectMapper;
import cz.mg.entity.mapper.value.IntegerObjectMapper;
import cz.mg.entity.mapper.value.StringObjectMapper;
import cz.mg.entity.services.factories.EntityClassFactory;


@SuppressWarnings({"rawtypes", "unchecked"})
public @Service class MapperFactory {
    private static MapperFactory instance;

    public static MapperFactory getInstance() {
        if (instance == null) {
            instance = new MapperFactory();
            instance.entityClassFactory = EntityClassFactory.getInstance();
        }
        return instance;
    }

    private EntityClassFactory entityClassFactory;

    private MapperFactory() {
    }

    public <T> @Mandatory Mapper<T> create(Class<?>... classes) {
        return create(new List<>(classes));
    }

    public <T> @Mandatory Mapper<T> create(@Mandatory List<Class<?>> classes) {
        List<ObjectMapper> objectMappers = new List<>();

        objectMappers.addLast(new BooleanObjectMapper());
        objectMappers.addLast(new IntegerObjectMapper());
        objectMappers.addLast(new StringObjectMapper());
        objectMappers.addLast(new ListObjectMapper());

        for(Class<?> clazz : classes){
            if (Enum.class.isAssignableFrom(clazz)) {
                objectMappers.addLast(new EnumObjectMapper(clazz));
            } else if (clazz.isAnnotationPresent(Entity.class)) {
                objectMappers.addLast(new EntityObjectMapper(entityClassFactory.create(clazz)));
            }
        }

        return new Mapper<>(objectMappers);
    }
}

package cz.mg.entity.mapper.collection;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.ObjectMapper;


@SuppressWarnings({"rawtypes", "unchecked"})
public class ListObjectMapper implements ObjectMapper<List> {
    @Override
    public @Mandatory String getName() {
        return List.class.getSimpleName();
    }

    @Override
    public @Mandatory List create(@Mandatory String value) {
        return new List();
    }

    @Override
    public @Mandatory String getValue(@Mandatory List object) {
        return "";
    }

    @Override
    public @Mandatory List<Object> getFields(@Mandatory List object) {
        return object;
    }

    @Override
    public void setFields(@Mandatory List object, @Mandatory List<Object> fields) {
        for (Object field : fields) {
            object.addLast(field);
        }
    }
}

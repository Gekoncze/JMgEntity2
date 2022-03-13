package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;


public @Utility interface ObjectMapper<T> {
    @Mandatory String getName();
    @Mandatory T create(@Optional String value);
    @Optional String getValue(@Mandatory T object);
    @Mandatory List<Object> getFields(@Mandatory T object);
    void setFields(@Mandatory T object, @Mandatory List<Object> fields);
}

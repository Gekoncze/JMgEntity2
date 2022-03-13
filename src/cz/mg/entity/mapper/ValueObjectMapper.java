package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


public @Utility interface ValueObjectMapper<T> extends ObjectMapper<T> {
    @Override
    default @Mandatory List<Object> getFields(@Mandatory T object){
        return new List<>();
    }

    @Override
    default void setFields(@Mandatory T object, @Mandatory List<Object> fields){
    }
}

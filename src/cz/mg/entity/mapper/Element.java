package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;


public @Entity class Element {
    private String name;
    private String value;
    private List<Integer> fields;

    public Element() {
    }

    public Element(String name, String value, List<Integer> fields) {
        this.name = name;
        this.value = value;
        this.fields = fields;
    }

    @Value
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Value
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Part
    public List<Integer> getFields() {
        return fields;
    }

    public void setFields(List<Integer> fields) {
        this.fields = fields;
    }
}

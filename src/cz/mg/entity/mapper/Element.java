package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;


public @Utility class Element {
    private @Mandatory @Value String name;
    private @Mandatory @Value String value;
    private @Mandatory @Value List<Integer> fields;

    public Element(@Mandatory String name, @Mandatory String value, @Mandatory List<Integer> fields) {
        this.name = name;
        this.value = value;
        this.fields = fields;
    }

    public @Mandatory String getName() {
        return name;
    }

    public void setName(@Mandatory String name) {
        this.name = name;
    }

    public @Mandatory String getValue() {
        return value;
    }

    public void setValue(@Mandatory String value) {
        this.value = value;
    }

    public @Mandatory List<Integer> getFields() {
        return fields;
    }

    public void setFields(@Mandatory List<Integer> fields) {
        this.fields = fields;
    }
}

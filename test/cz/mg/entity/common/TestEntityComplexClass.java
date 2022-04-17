package cz.mg.entity.common;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;

public @Entity class TestEntityComplexClass {
    private @Value Integer value;
    private @Value TestEnum testEnum;
    private @Part TestEntityClass testEntityClass;
    private @Link TestEntityComplexClass testEntityComplexClass;

    public TestEntityComplexClass() {
    }

    public TestEntityComplexClass(
        Integer value,
        TestEnum testEnum,
        TestEntityClass testEntityClass,
        TestEntityComplexClass testEntityComplexClass
    ) {
        this.value = value;
        this.testEnum = testEnum;
        this.testEntityClass = testEntityClass;
        this.testEntityComplexClass = testEntityComplexClass;
    }

    @Value
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Value
    public TestEnum getTestEnum() {
        return testEnum;
    }

    public void setTestEnum(TestEnum testEnum) {
        this.testEnum = testEnum;
    }

    @Part
    public TestEntityClass getTestEntityClass() {
        return testEntityClass;
    }

    public void setTestEntityClass(TestEntityClass testEntityClass) {
        this.testEntityClass = testEntityClass;
    }

    @Link
    public TestEntityComplexClass getTestEntityComplexClass() {
        return testEntityComplexClass;
    }

    public void setTestEntityComplexClass(TestEntityComplexClass testEntityComplexClass) {
        this.testEntityComplexClass = testEntityComplexClass;
    }
}

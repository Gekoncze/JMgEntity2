package cz.mg.entity.common.invalid;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;

public @Entity interface TestEntityInterfaceWithInvalidFields {
    @Value
    String getInvalidZero();

    @Value
    void getInvalidOne();
    void setInvalidOne(String value);

    @Value
    String getInvalidTwo();
    void setInvalidTwo();

    @Value
    String getInvalidThree(String value);
    void setInvalidThree(String value);

    @Value
    String getInvalidFour();
    String setInvalidFour(String value);

    @Value
    String getInvalidFive();
    void setInvalidFive(String value, String value2);

    @Value
    String getInvalidSix();
    void setInvalidSix(Integer value);

    @Value
    int getInvalidSeven();
    void setInvalidSeven(int value);

    @Value
    String betInvalidEight();
    void setInvalidEight(String value);
}

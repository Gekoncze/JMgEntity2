package cz.mg.entity.services.validators;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.invalid.TestEntityInterfaceWithoutAnnotation;
import cz.mg.test.Assert;

public @Test class EntityInterfaceValidatorTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityInterfaceValidatorTest.class.getSimpleName() + " ... ");

        EntityInterfaceValidatorTest test = new EntityInterfaceValidatorTest();
        test.testValidateAnnotation();

        System.out.println("OK");
    }

    private void testValidateAnnotation() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityClassValidator.getInstance().validate(
                TestEntityInterfaceWithoutAnnotation.class
            );
        });
    }
}

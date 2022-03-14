package cz.mg.entity.services.validators;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.invalid.TestEntityClassWithoutAnnotation;
import cz.mg.entity.common.invalid.TestEntityClassWithoutParameterLessConstructor;
import cz.mg.entity.common.invalid.TestEntityClassWithoutPublicConstructor;
import cz.mg.test.Assert;

public @Test class EntityClassValidatorTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityClassValidatorTest.class.getSimpleName() + " ... ");

        EntityClassValidatorTest test = new EntityClassValidatorTest();
        test.testValidateConstructor();
        test.testValidateAnnotation();

        System.out.println("OK");
    }

    private void testValidateAnnotation() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityClassValidator.getInstance().validate(
                TestEntityClassWithoutAnnotation.class
            );
        });
    }

    private void testValidateConstructor() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityClassValidator.getInstance().validate(
                TestEntityClassWithoutPublicConstructor.class
            );
        });

        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityClassValidator.getInstance().validate(
                TestEntityClassWithoutParameterLessConstructor.class
            );
        });
    }
}

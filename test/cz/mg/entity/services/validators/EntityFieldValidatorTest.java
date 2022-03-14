package cz.mg.entity.services.validators;

import cz.mg.annotations.classes.Test;
import cz.mg.entity.common.invalid.TestEntityInterfaceWithInvalidFields;
import cz.mg.test.Assert;
import cz.mg.test.AssertException;

public @Test class EntityFieldValidatorTest {
    public static void main(String[] args) {
        System.out.print("Running " + EntityFieldValidatorTest.class.getSimpleName() + " ... ");

        EntityFieldValidatorTest test = new EntityFieldValidatorTest();
        test.testValidateMissingSetter();
        test.testValidateMissingGetterReturnValue();
        test.testValidateMissingSetterParameter();
        test.testValidateUnexpectedGetterParameter();
        test.testValidateUnexpectedSetterReturnValue();
        test.testValidateUnexpectedSetterMultipleParameters();
        test.testValidateTypeMismatch();
        test.testValidateUnexpectedPrimitive();
        test.testValidateInvalidName();

        System.out.println("OK");
    }

    private void testValidateMissingSetter() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityFieldValidator.getInstance().validate(
                TestEntityInterfaceWithInvalidFields.class,
                "InvalidZero",
                String.class
            );
        });
    }

    private void testValidateMissingGetterReturnValue() {
        testIsNotMissingFieldError(
            Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
                EntityFieldValidator.getInstance().validate(
                    TestEntityInterfaceWithInvalidFields.class,
                    "InvalidOne",
                    String.class
                );
            })
        );
    }

    private void testValidateMissingSetterParameter() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityFieldValidator.getInstance().validate(
                TestEntityInterfaceWithInvalidFields.class,
                "InvalidTwo",
                String.class
            );
        });
    }

    private void testValidateUnexpectedGetterParameter() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityFieldValidator.getInstance().validate(
                TestEntityInterfaceWithInvalidFields.class,
                "InvalidThree",
                String.class
            );
        });
    }

    private void testValidateUnexpectedSetterReturnValue() {
        testIsNotMissingFieldError(
            Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
                EntityFieldValidator.getInstance().validate(
                    TestEntityInterfaceWithInvalidFields.class,
                    "InvalidFour",
                    String.class
                );
            })
        );
    }

    private void testValidateUnexpectedSetterMultipleParameters() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityFieldValidator.getInstance().validate(
                TestEntityInterfaceWithInvalidFields.class,
                "InvalidFive",
                String.class
            );
        });
    }

    private void testValidateTypeMismatch() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityFieldValidator.getInstance().validate(
                TestEntityInterfaceWithInvalidFields.class,
                "InvalidSix",
                String.class
            );
        });
    }

    private void testValidateUnexpectedPrimitive() {
        testIsNotMissingFieldError(
            Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
                EntityFieldValidator.getInstance().validate(
                    TestEntityInterfaceWithInvalidFields.class,
                    "InvalidSeven",
                    int.class
                );
            })
        );

        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityFieldValidator.getInstance().validate(
                TestEntityInterfaceWithInvalidFields.class,
                "InvalidSeven",
                Integer.class
            );
        });
    }

    private void testValidateInvalidName() {
        Assert.assertExceptionThrown(IllegalArgumentException.class, () -> {
            EntityFieldValidator.getInstance().validate(
                TestEntityInterfaceWithInvalidFields.class,
                "InvalidEight",
                String.class
            );
        });
    }

    private void testIsNotMissingFieldError(IllegalArgumentException e) {
        if (e.getMessage().startsWith("Could not find")) {
            throw new AssertException("Unexpected exception message.");
        }
    }
}

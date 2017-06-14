package dk.sunepoulsen.mycash.ui.model

import org.junit.BeforeClass
import org.junit.Test

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.time.LocalDate;

class AccountingTest {
    private static Validator validator

    @BeforeClass
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    @Test
    void testValidation_NameIsNull() {
        Accounting instance = new Accounting()
        instance.startDate = LocalDate.now()
        instance.endDate = LocalDate.now()

        Set<ConstraintViolation<Accounting>> violations = validator.validate( instance )

        assert violations.stream()
            .filter {
                it -> it.propertyPath.find { node -> node.name == "name" } != null
            }
            .findFirst()
            .isPresent()
    }

    @Test
    void testValidation_StartDateIsNull() {
        Accounting instance = new Accounting()
        instance.name = "name"
        instance.endDate = LocalDate.now()

        Set<ConstraintViolation<Accounting>> violations = validator.validate( instance )

        assert violations.stream()
            .filter {
            it -> it.propertyPath.find { node -> node.name == "startDate" } != null
        }
        .findFirst()
            .isPresent()
    }

    @Test
    void testValidation_EndDateIsNull() {
        Accounting instance = new Accounting()
        instance.name = "name"
        instance.startDate = LocalDate.now()

        Set<ConstraintViolation<Accounting>> violations = validator.validate( instance )

        assert violations.stream()
            .filter {
            it -> it.propertyPath.find { node -> node.name == "endDate" } != null
        }
        .findFirst()
            .isPresent()
    }
}

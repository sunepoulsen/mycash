package dk.sunepoulsen.mycash.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by sunepoulsen on 14/06/2017.
 */
public class MyCashValidation<T> {
    private Validator validator;

    public MyCashValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public Set<ConstraintViolation<T>> validate( T value ) {
        return validator.validate( value );
    }

    public void validateAndThrow( T value ) throws MyCashValidateException {
        Set<ConstraintViolation<T>> violations = validate( value );
        if( violations.isEmpty() ) {
            return;
        }

        throw new MyCashValidateException( violations );
    }

    public static <T> void validateValue( T value ) throws MyCashValidateException {
        new MyCashValidation<T>().validateAndThrow( value );
    }
}

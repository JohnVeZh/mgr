package cn.sparke.core.common.utils;

import org.apache.poi.ss.formula.functions.T;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatedUtil {
    private static Validator validator;
    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> ValidateResult validator(T object) {
        if (null == object) {
            return new ValidateResult(false,
                    "The object to be validated must not be null.");
        }

        Set<ConstraintViolation<T>> violations = validator.validate(object);
        int errSize = violations.size();

        StringBuilder sb = new StringBuilder();
        boolean result = true;
        if (errSize > 0) {
            for (ConstraintViolation<T> violation : violations) {
                sb.append(violation.getMessage()).append(";") ;
            }
            result = false;
        }
        return new ValidateResult(result, sb.toString());
    }

    public static class ValidateResult{
        public ValidateResult(boolean valid, String errMsg){
            this.valid = valid;
            this.errMsg = errMsg;
        }
        private boolean valid;
        private String errMsg;

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }
    }

}

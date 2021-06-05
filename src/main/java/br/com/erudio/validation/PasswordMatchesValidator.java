package br.com.erudio.validation;

import br.com.erudio.data.vo.v1.UserVO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserVO user = (UserVO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}

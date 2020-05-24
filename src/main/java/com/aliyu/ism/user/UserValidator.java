package com.aliyu.ism.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lga", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "martialStatus", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "");

        User user = (User) target;
        Calendar calendar = Calendar.getInstance();
//        int currentYear = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.YEAR, -18);
//        System.out.format("%s %s", calendar.get((Calendar.YEAR)), user.getDob());
        /*if (user.getDob().after(Date.from(calendar.toInstant())))
            errors.rejectValue("dob", "dob.underAge");
        if (user.isAuthenticated()) {
            errors.rejectValue("authenticated", "authenticated.false");
        }*/
        switch (user.getId().substring(0, 3)) {
            case "VTR":
                validateVoter(user, errors);
                break;
            case "ADM":
                validateAdmin(user, errors);
                break;
            case "CDT":
                validateCandidate(user, errors);
                break;
        }
    }

    private void validateAdmin(User user, Errors error) {
        if (user.getParty() != null) {
            error.rejectValue("party", "admin.party.notnull");
        } else if (user.getPost() != null) {
            error.rejectValue("post", "admin.post.notnull");
        }
    }

    private void validateVoter(User user, Errors error) {
        if (user.getParty() != null) {
            error.rejectValue("party", "voter.party.notnull");
        } else if (user.getPost() != null) {
            error.rejectValue("post", "voter.post.notnull");
        }
    }

    private void validateCandidate(User user, Errors error) {
        if (user.getParty() == null) {
            error.rejectValue("party", "candidate.party.null");
        }
        if (user.getPost() == null) {
            error.rejectValue("post", "candidate.post.null");
        }
    }
}

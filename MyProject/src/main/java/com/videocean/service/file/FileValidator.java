package com.videocean.service.file;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        FileBucket fileBucket = (FileBucket) obj;

        if (fileBucket.getFile() == null || fileBucket.getName() == null || fileBucket.getName().isEmpty()) {
            errors.rejectValue("file", "missing.file");
        } else if (fileBucket.getFile() != null) {
            if (fileBucket.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}
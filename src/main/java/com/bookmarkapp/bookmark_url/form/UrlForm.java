package com.bookmarkapp.bookmark_url.form;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UrlForm {
    @NotBlank(message = "Necessary information")
    @URL
    private String address;

    @Size(max = 500)
    private String description;
}

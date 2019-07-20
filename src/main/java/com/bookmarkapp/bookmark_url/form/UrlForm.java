package com.bookmarkapp.bookmark_url.form;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UrlForm {
    @NotBlank(message = "Not empty")
    @URL(message = "Invalid url")
    private String address;

    @Size(max = 500, message= "Description too long")
    private String description;

    private Integer[] tagIds;

    private Integer[] subTagIds;
}

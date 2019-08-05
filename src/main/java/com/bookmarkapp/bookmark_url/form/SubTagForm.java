package com.bookmarkapp.bookmark_url.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SubTagForm {
    @NotBlank(message = "Not empty")
    private String title;

    @Size(max = 500, message= "Description too long")
    private String description;
}

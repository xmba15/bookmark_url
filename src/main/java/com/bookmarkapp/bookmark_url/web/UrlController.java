package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.Url;
import com.bookmarkapp.bookmark_url.form.UrlForm;
import com.bookmarkapp.bookmark_url.service.UrlService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("urls")
public class UrlController {
    @Autowired
    UrlService urlService;

    @ModelAttribute
    UrlForm setUpForm() {
        return new UrlForm();
    }

    @GetMapping
    String list(Model model) {
        List<Url> urls = urlService.findAll();
        model.addAttribute("urls", urls);

        return "urls/list";
    }

    @PostMapping(path = "create")
    String create(@Validated UrlForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }

        Url url = new Url();
        BeanUtils.copyProperties(form, url);
        urlService.create(url);

        return "redirect:/urls";
    }
}

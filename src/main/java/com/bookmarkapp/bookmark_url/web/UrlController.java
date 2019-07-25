package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.Url;
import com.bookmarkapp.bookmark_url.form.UrlForm;
import com.bookmarkapp.bookmark_url.service.TagService;
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
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("urls")
public class UrlController {
    @Autowired
    UrlService urlService;

    @Autowired
    TagService tagService;

    @ModelAttribute
    UrlForm setUpForm() {
        return new UrlForm();
    }

    final static String OTHER_TAG = "miscellaneous";

    @GetMapping
    String list(Model model) {
        List<Url> urls = urlService.findAll();
        model.addAttribute("urls", urls);

        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);

        return "urls/list";
    }

    @PostMapping(path = "create")
    String create(@Validated UrlForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }

        Set<Tag> checkedTags = tagService.findTagsByIds(form.getTagIds());
        if (checkedTags.isEmpty()) {
            Tag tag = tagService.findOneByTitle(OTHER_TAG).get();
            checkedTags.add(tag);
        }

        Optional<Url> duplicateUrl = urlService.findOneByAddress(form.getAddress());

        Url urlToSave;

        if (duplicateUrl.isPresent()) {
            urlToSave = duplicateUrl.get();
        } else {
            urlToSave = new Url();
            urlToSave.setAddress(form.getAddress());
        }

        if (!form.getDescription().isEmpty()) {
            urlToSave.setDescription(form.getDescription());
        }

        urlToSave.setTags(checkedTags);

        urlService.create(urlToSave);

        return "redirect:/urls";
    }
}

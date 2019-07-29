package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.Url;
import com.bookmarkapp.bookmark_url.domain.UrlTag;
import com.bookmarkapp.bookmark_url.form.UrlForm;
import com.bookmarkapp.bookmark_url.service.TagService;
import com.bookmarkapp.bookmark_url.service.UrlService;
import com.bookmarkapp.bookmark_url.service.UrlTagService;
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

import java.util.HashSet;
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

    @Autowired
    UrlTagService urlTagService;

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

        try {
            Set<Tag> checkedTags;
            if (form.getTagIds().length != 0) {
                checkedTags = tagService.findTagsByIds(form.getTagIds());
            } else {
                checkedTags = new HashSet<>();
            }

            Url urlToSave;
            Optional<Url> duplicateUrl = urlService.findOneByAddress(form.getAddress());
            if (duplicateUrl.isPresent()) {
                urlToSave = duplicateUrl.get();
                Set<Tag> currentTags = urlToSave.getTags();
                Set<Tag> intersectTag = new HashSet<>(checkedTags);
                intersectTag.retainAll(currentTags);

                currentTags.removeAll(intersectTag);
                for (Tag currentTag : currentTags) {
                    UrlTag urlTagToDel = urlTagService.findOneByUrlIdTagId(urlToSave.getId(), currentTag.getId()).get();
                    urlTagService.delete(urlTagToDel);
                }
                checkedTags.removeAll(intersectTag);
            } else {
                urlToSave = new Url();
                urlToSave.setAddress(form.getAddress());
            }

            for (Tag checkedTag: checkedTags) {
                UrlTag urlTag = new UrlTag();
                urlTag.setTag(checkedTag);
                urlTag.setUrl(urlToSave);
                urlTagService.create(urlTag);
            }

            urlToSave.setDescription(form.getDescription());
            urlService.create(urlToSave);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/urls";
        }

        return "redirect:/urls";

    }
}

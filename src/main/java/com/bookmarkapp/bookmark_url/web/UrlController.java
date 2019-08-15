package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.Url;
import com.bookmarkapp.bookmark_url.domain.UrlTag;
import com.bookmarkapp.bookmark_url.form.UrlForm;
import com.bookmarkapp.bookmark_url.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    SubTagService subTagService;

    @Autowired
    UrlTagService urlTagService;

    @Autowired
    UrlSubTagService urlSubTagService;

    @ModelAttribute
    UrlForm setUpForm() {
        return new UrlForm();
    }

    final static String OTHER_TAG = "miscellaneous";

    @RequestMapping(method = RequestMethod.GET)
    String list(Model model, Pageable pageable) {
        Page<Url> urls = urlService.findAll(pageable);
        model.addAttribute("urls", urls.getContent());
        model.addAttribute("numUrls", urls.getTotalElements());
        model.addAttribute("pageInfo", urls);

        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);

        return "urls/list";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    String create(@Validated UrlForm form, BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
            return list(model, pageable);
        }

        try {
            Set<Tag> checkedTags = (form.getTagIds().length != 0) ? tagService.findTagsByIds(form.getTagIds()) : new HashSet<>();
            Set<SubTag> checkedSubTags = (form.getSubTagIds().length != 0) ? subTagService.findSubTagsByIds(form.getSubTagIds()) : new HashSet<>();

            Optional<Url> duplicateUrl = urlService.findOneByAddress(form.getAddress());
            Url urlToSave = duplicateUrl.isPresent() ? duplicateUrl.get() : new Url(form.getAddress());

            if (form.getDescription() != null) {
                urlToSave.setDescription(form.getDescription());
            }

            if (!checkedTags.equals(urlToSave.getTags())) {
                urlToSave.setTags(checkedTags);
            }

            if (!checkedSubTags.equals(urlToSave.getSubTags())) {
                urlToSave.setSubTags(checkedSubTags);
            }

            urlService.create(urlToSave);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/urls";
        }

        return "redirect:/urls";
    }

    @RequestMapping(path = "/delete/{urlId}", method = RequestMethod.DELETE)
    String deleteUrl(@PathVariable Long urlId) {
        Optional<Url> url = urlService.findOne(urlId);
        if (!url.isPresent()) {
            return "redirect:/urls";
        }

        urlTagService.deleteAllByUrlId(urlId);
        urlSubTagService.deleteAllByUrlId(urlId);
        urlService.delete(urlId);

        return "redirect:/urls";
    }
}

package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.TagSubTag;
import com.bookmarkapp.bookmark_url.form.SubTagForm;
import com.bookmarkapp.bookmark_url.form.TagForm;
import com.bookmarkapp.bookmark_url.form.UrlForm;
import com.bookmarkapp.bookmark_url.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;

    @Autowired
    SubTagService subTagService;

    @Autowired
    TagSubTagService tagSubTagService;

    @Autowired
    UrlTagService urlTagService;

    @Autowired
    UrlSubTagService urlSubTagService;

    @ModelAttribute("tagForm")
    TagForm setUpTagForm() {
        return new TagForm();
    }

    @ModelAttribute("subTagForm")
    SubTagForm setUpSubTagForm() {
        return new SubTagForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    String list(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);

        return "tags/list";
    }

    @RequestMapping(path = "{tagId}", method = RequestMethod.GET)
    String getTag(@PathVariable Integer tagId, Model model) {
        Optional<Tag> tag = tagService.findOne(tagId);
        if (tag.isPresent()) {
            model.addAttribute("targetTag", tag.get());
            model.addAttribute("targetSubtags", tag.get().getSubTags());
            return "tags/info";
        } else {
            return "redirect:/tags";
        }
    }

    @RequestMapping(path = "/delete/{tagId}", method = RequestMethod.DELETE)
    String deleteTag(@PathVariable Integer tagId) {
        Optional<Tag> tag = tagService.findOne(tagId);
        if (!tag.isPresent()) {
            return "redirect:/tags";
        }

        tagSubTagService.deleteAllSubTagsByTagId(tagId);
        for (SubTag subTag : tag.get().getSubTags()) {
            urlSubTagService.deleteAllBySubTagId(subTag.getId());
        }

        urlTagService.deleteAllByTagId(tagId);
        tagService.delete(tagId);

        return "redirect:/tags";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    String createTag(@Validated TagForm tagForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }

        try {
            String title = tagForm.getTitle();
            Optional<Tag> duplicateTag = tagService.findOneByTitle(title);
            if (duplicateTag.isPresent()) {
                return "redirect:/tags";
            }

            Tag newTag = new Tag();
            newTag.setTitle(title);
            if (tagForm.getDescription() != null) {
                newTag.setDescription(tagForm.getDescription());
            }

            tagService.save(newTag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/tags";
        }

        return "redirect:/tags";
    }

    @RequestMapping(path = "{tagId}/subtags/create", method = RequestMethod.POST)
    String createSubTag(@Validated SubTagForm subTagForm, BindingResult result, @PathVariable("tagId") Integer tagId, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }

        try {
            Optional<Tag> tagToAddSubTag = tagService.findOne(tagId);
            if (!tagToAddSubTag.isPresent()) {
                return "redirect:/tags";
            }

            String subTagTitle = subTagForm.getTitle();
            Optional<TagSubTag> duplicateTagSubTag = tagSubTagService.findOneByTagIdSubTagTitle(tagId, subTagTitle);
            if (duplicateTagSubTag.isPresent()) {
                return "redirect:/tags" + tagId;
            }

            SubTag newSubTag = new SubTag();
            newSubTag.setTitle(subTagTitle);
            if (subTagForm.getDescription() != null) {
                newSubTag.setDescription(subTagForm.getDescription());
            }
            subTagService.save(newSubTag);

            TagSubTag newTagSubTag = new TagSubTag();
            newTagSubTag.setTag(tagToAddSubTag.get());
            newTagSubTag.setSubTag(newSubTag);
            tagSubTagService.save(newTagSubTag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/tags/" + tagId;
        }

        return "redirect:/tags/" + tagId;
    }
}

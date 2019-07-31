package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    String list(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);

        return "tags/list";
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    String getTag(@PathVariable Integer id, Model model) {
        Optional<Tag> tag = tagService.findOne(id);
        if (tag.isPresent()) {
            model.addAttribute("targetTag", tag.get());
            model.addAttribute("targetSubtags", tag.get().getSubtags());
            return "tags/info";
        } else {
            return "redirect:/tags";
        }
    }

    @RequestMapping(path= "{id}", method = RequestMethod.DELETE)
    String deleteTag(@PathVariable Integer id) {
        tagService.delete(id);
        return "redirect:/tags";
    }
}

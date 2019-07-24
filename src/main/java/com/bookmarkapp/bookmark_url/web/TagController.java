package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping
    String list(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);

        return "tags/list";
    }
}

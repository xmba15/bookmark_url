package com.bookmarkapp.bookmark_url.api;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.exception.TagNotFoundException;
import com.bookmarkapp.bookmark_url.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tags")
public class TagRestController {
    @Autowired
    TagService tagService;

    @GetMapping
    List<Tag> getTags() {
        return tagService.findAll();
    }

    @GetMapping(path = "{id}")
    Tag getTag(@PathVariable Integer id) {
        return tagService.findOne(id).orElseThrow(()->new TagNotFoundException(id));
    }
}
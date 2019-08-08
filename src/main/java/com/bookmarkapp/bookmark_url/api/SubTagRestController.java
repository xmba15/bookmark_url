package com.bookmarkapp.bookmark_url.api;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import com.bookmarkapp.bookmark_url.exception.SubTagNotFoundException;
import com.bookmarkapp.bookmark_url.service.SubTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/subtags")
public class SubTagRestController {
    @Autowired
    SubTagService subTagService;

    @GetMapping
    List<SubTag> getSubTags() {
        return subTagService.findAll();
    }

    @GetMapping(path = "{id}")
    SubTag getSubTag(@PathVariable Integer id) {
        return subTagService.findOne(id).orElseThrow(()->new SubTagNotFoundException(id));
    }
}

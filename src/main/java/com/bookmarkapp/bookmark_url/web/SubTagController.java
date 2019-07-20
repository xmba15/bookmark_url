package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.TagSubTag;
import com.bookmarkapp.bookmark_url.domain.UrlSubTag;
import com.bookmarkapp.bookmark_url.repository.TagSubTagRepository;
import com.bookmarkapp.bookmark_url.repository.UrlSubTagRepository;
import com.bookmarkapp.bookmark_url.service.SubTagService;
import com.bookmarkapp.bookmark_url.service.TagSubTagService;
import com.bookmarkapp.bookmark_url.service.UrlSubTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/subtags")
public class SubTagController {
    @Autowired
    SubTagService subTagService;

    @Autowired
    TagSubTagService tagSubTagService;

    @Autowired
    UrlSubTagService urlSubTagService;

   @RequestMapping(path = "/delete/{subTagId}", method = RequestMethod.DELETE)
   String deleteSubTag(@PathVariable Integer subTagId) {
       Optional<Tag> tag = tagSubTagService.findTagBySubTagId(subTagId);
       if (tag.isPresent()) {
           Optional<TagSubTag> tagSubTag = tagSubTagService.findOneByTagIdSubTagId(tag.get().getId(), subTagId);
           tagSubTagService.delete(tagSubTag.get());
       }

       urlSubTagService.deleteAllBySubTagId(subTagId);

       subTagService.delete(subTagId);

       if (tag.isPresent()) {
           return "redirect:/tags/" + tag.get().getId();
       } else {
           return "redirect:/tags";
       }
   }
}

package com.bookmarkapp.bookmark_url.web;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.TagSubTag;
import com.bookmarkapp.bookmark_url.repository.TagSubTagRepository;
import com.bookmarkapp.bookmark_url.service.SubTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/subtags")
public class SubTagController {
    @Autowired
    SubTagService subTagService;

    @Autowired
    TagSubTagRepository tagSubTagRepository;

   @RequestMapping(path = "/delete/{subTagId}", method = RequestMethod.DELETE)
   String deleteSubTag(@PathVariable Integer subTagId) {
       Optional<Tag> tag = tagSubTagRepository.findTagBySubTagId(subTagId);
       if (tag.isPresent()) {
           Optional<TagSubTag> tagSubTag = tagSubTagRepository.findOneByTagIdSubTagId(tag.get().getId(), subTagId);
           tagSubTagRepository.delete(tagSubTag.get());
       }

       subTagService.delete(subTagId);

       if (tag.isPresent()) {
           return "redirect:/tags/" + tag.get().getId();
       } else {
           return "redirect:/tags";
       }
   }
}

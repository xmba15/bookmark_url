package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.TagSubTag;
import com.bookmarkapp.bookmark_url.repository.SubTagRepository;
import com.bookmarkapp.bookmark_url.repository.TagSubTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TagSubTagService {
    @Autowired
    TagSubTagRepository tagSubTagRepository;

    @Autowired
    SubTagRepository subTagRepository;

    public Optional<Tag> findTagBySubTagId(Integer subTagId) {
        return tagSubTagRepository.findTagBySubTagId(subTagId);
    }

    public Optional<TagSubTag> findOneByTagIdSubTagId(Integer tagId, Integer subTagId) {
        return tagSubTagRepository.findOneByTagIdSubTagId(tagId, subTagId);
    }

    public void delete(TagSubTag tagSubTag) {
        tagSubTagRepository.delete(tagSubTag);
    }

    public void delete(Integer id) {
        tagSubTagRepository.deleteById(id);
    }

    public TagSubTag save(TagSubTag tagSubTag) {
        return tagSubTagRepository.save(tagSubTag);
    }

    public Optional<TagSubTag> findOneByTagIdSubTagTitle(Integer tagId, String subTagTitle) {
        return tagSubTagRepository.findOneByTagIdSubTagTitle(tagId, subTagTitle);
    }

    public List<TagSubTag> findAllTagSubTagsByTagId(Integer tagId) {
        return tagSubTagRepository.findAllTagSubTagsByTagId(tagId);
    }

    public void deleteAllSubTagsByTagId(Integer tagId) {
        List<TagSubTag> tagSubTags = tagSubTagRepository.findAllTagSubTagsByTagId(tagId);
        for (TagSubTag tagSubTag : tagSubTags) {
            tagSubTagRepository.delete(tagSubTag);
            subTagRepository.delete(tagSubTag.getSubTag());
        }
    }
}

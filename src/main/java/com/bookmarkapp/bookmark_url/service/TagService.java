package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }
}
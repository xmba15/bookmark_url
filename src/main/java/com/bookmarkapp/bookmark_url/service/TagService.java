package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.exception.TagNotFoundException;
import com.bookmarkapp.bookmark_url.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAllOrderById();
    }

    public Optional<Tag> findOne(Integer id) {
        return tagRepository.findById(id);
    }
}

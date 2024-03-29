package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.exception.TagNotFoundException;
import com.bookmarkapp.bookmark_url.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Set<Tag> findTagsByIds(Integer[] ids) {
        return tagRepository.findTagsByIds(ids);
    }

    public Optional<Tag> findOneByTitle(String title) {
        return tagRepository.findOneByTitle(title);
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    public void delete(Integer id) {
        tagRepository.deleteById(id);
    }
}

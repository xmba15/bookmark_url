package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import com.bookmarkapp.bookmark_url.repository.SubTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SubTagService {
    @Autowired
    SubTagRepository subTagRepository;

    public List<SubTag> findAll() {
        return subTagRepository.findAllOrderById();
    }

    public Optional<SubTag> findOne(Integer id) {
        return subTagRepository.findById(id);
    }
}

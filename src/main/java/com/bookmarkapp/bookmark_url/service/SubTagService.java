package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import com.bookmarkapp.bookmark_url.repository.SubTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Set<SubTag> findSubTagsByIds(Integer[] ids) {
        return subTagRepository.findSubTagsByIds(ids);
    }

    public SubTag save(SubTag subTag) {
        return subTagRepository.save(subTag);
    }

    public void delete(SubTag subTag) {
        subTagRepository.delete(subTag);
    }

    public void delete(Integer id) {
        subTagRepository.deleteById(id);
    }
}

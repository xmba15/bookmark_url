package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.Url;
import com.bookmarkapp.bookmark_url.exception.UrlNotFoundException;
import com.bookmarkapp.bookmark_url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    public List<Url> findAll() {
        return urlRepository.findAllOrderById();
    }

    public Optional<Url> findOne(Long id) {
        return urlRepository.findById(id);
    }

    public Url create(Url url) {
        return urlRepository.save(url);
    }

    public Optional<Url> findOneByAddress(String address) {
        return urlRepository.findOneByAddress(address);
    }
}

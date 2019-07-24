package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;
}

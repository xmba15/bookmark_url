package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.UrlSubTag;
import com.bookmarkapp.bookmark_url.repository.UrlSubTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UrlSubTagService {
    @Autowired
    UrlSubTagRepository urlSubTagRepository;

    public UrlSubTag create(UrlSubTag urlSubTag) {
        return urlSubTagRepository.save(urlSubTag);
    }

    public void delete(UrlSubTag urlSubTag) {
        urlSubTagRepository.delete(urlSubTag);
    }

    public void deleteAllBySubTagId(Integer subTagId) {
        List<UrlSubTag> urlSubTags = urlSubTagRepository.findAllBySubTagId(subTagId);
        for (UrlSubTag urlSubTag : urlSubTags) {
            urlSubTagRepository.delete(urlSubTag);
        }
    }

    public void deleteAllByUrlId(Long urlId) {
        List<UrlSubTag> urlSubTags = urlSubTagRepository.findAllByUrlId(urlId);
        for (UrlSubTag urlSubTag : urlSubTags) {
            urlSubTagRepository.delete(urlSubTag);
        }
    }
}

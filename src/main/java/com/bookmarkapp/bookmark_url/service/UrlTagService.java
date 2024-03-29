package com.bookmarkapp.bookmark_url.service;

import com.bookmarkapp.bookmark_url.domain.UrlTag;
import com.bookmarkapp.bookmark_url.repository.UrlTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UrlTagService {
    @Autowired
    UrlTagRepository urlTagRepository;

    public UrlTag create(UrlTag urlTag) {
        return urlTagRepository.save(urlTag);
    }

    public Optional<UrlTag> findOneByUrlIdTagId(Long urlId, Integer tagId) {
        return urlTagRepository.findOneByUrlIdTagId(urlId, tagId);
    }

    public void delete(UrlTag urlTag) {
        urlTagRepository.delete(urlTag);
    }

    public void deleteAllByTagId(Integer tagId) {
        List<UrlTag> urlTags = urlTagRepository.findAllByTagId(tagId);
        for (UrlTag urlTag : urlTags) {
            urlTagRepository.delete(urlTag);
        }
    }

    public void deleteAllByUrlId(Long urlId) {
        List<UrlTag> urlTags = urlTagRepository.findAllByUrlId(urlId);
        for (UrlTag urlTag : urlTags) {
            urlTagRepository.delete(urlTag);
        }
    }
}

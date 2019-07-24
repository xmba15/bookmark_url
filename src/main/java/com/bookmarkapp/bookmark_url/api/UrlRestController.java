package com.bookmarkapp.bookmark_url.api;

import com.bookmarkapp.bookmark_url.domain.Url;
import com.bookmarkapp.bookmark_url.exception.UrlNotFoundException;
import com.bookmarkapp.bookmark_url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/urls")
public class UrlRestController {
    @Autowired
    UrlService urlService;

    @GetMapping
    List<Url> getUrls() {
        return urlService.findAll();
    }

    @GetMapping(path = "{id}")
    Url getUrl(@PathVariable Long id) {
        return urlService.findOne(id).orElseThrow(()->new UrlNotFoundException(id));
    }
}

package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.UrlTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UrlTagRepository extends JpaRepository<UrlTag, Integer> {

}

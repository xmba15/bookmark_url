package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.UrlSubTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UrlSubTagRepository extends JpaRepository<UrlSubTag, Integer> {
    @Query("SELECT x FROM UrlSubTag x WHERE x.subTag.id  = :subTagId")
    List<UrlSubTag> findAllBySubTagId(@Param("subTagId") Integer subTagId);

    @Query("SELECT x FROM UrlSubTag x WHERE x.url.id = :urlId")
    List<UrlSubTag> findAllByUrlId(@Param("urlId") Long urlId);
}

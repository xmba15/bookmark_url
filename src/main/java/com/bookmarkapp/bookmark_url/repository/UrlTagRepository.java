package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.UrlTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UrlTagRepository extends JpaRepository<UrlTag, Integer> {
    @Query("SELECT x from UrlTag x WHERE x.url.id = :urlId AND x.tag.id = :tagId")
    Optional<UrlTag> findOneByUrlIdTagId(@Param("urlId") Long urlId, @Param("tagId") Integer tagId);

    @Query("SELECT x from UrlTag x WHERE x.tag.id = :tagId")
    List<UrlTag> findAllByTagId(@Param("tagId") Integer tagId);

    @Query("SELECT x from UrlTag x WHERE x.url.id = :urlId")
    List<UrlTag> findAllByUrlId(@Param("urlId") Long urlId);
}

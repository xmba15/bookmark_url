package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.Tag;
import com.bookmarkapp.bookmark_url.domain.TagSubTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface TagSubTagRepository extends JpaRepository<TagSubTag, Integer> {
    @Query("SELECT x.tag from TagSubTag x WHERE x.subTag.id = :subTagId")
    Optional<Tag> findTagBySubTagId(@Param("subTagId") Integer subTagId);

    @Query("SELECT x from TagSubTag x WHERE x.tag.id = :tagId AND x.subTag.id = :subTagId")
    Optional<TagSubTag> findOneByTagIdSubTagId(@Param("tagId") Integer tagId, @Param("subTagId") Integer subTagId);

    @Query("SELECT x from TagSubTag x WHERE x.tag.id = :tagId AND x.subTag.title = :subTagTitle")
    Optional<TagSubTag> findOneByTagIdSubTagTitle(@Param("tagId") Integer tagId, @Param("subTagTitle") String subTagTitle);
}

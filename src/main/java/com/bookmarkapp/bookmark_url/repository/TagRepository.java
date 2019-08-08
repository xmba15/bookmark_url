package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT x FROM Tag x ORDER BY x.id")
    List<Tag> findAllOrderById();

    @Query("SELECT x.title FROM Tag x ORDER BY x.id")
    List<String> findAllTitlesOrderById();

    @Query("SELECT x FROM Tag x WHERE x.id IN :ids")
    Set<Tag> findTagsByIds(@Param("ids") Integer[] ids);

    @Query("SELECT x FROM Tag x WHERE x.title = :title")
    Optional<Tag> findOneByTitle(@Param("title") String title);
}

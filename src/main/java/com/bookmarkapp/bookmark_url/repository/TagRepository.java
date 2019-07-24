package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT x FROM Tag x ORDER BY x.id")
    List<Tag> findAllOrderById();
}

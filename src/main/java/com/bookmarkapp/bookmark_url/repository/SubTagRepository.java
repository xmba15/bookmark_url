package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface SubTagRepository extends JpaRepository<SubTag, Integer> {
    @Query("SELECT x FROM SubTag x ORDER BY x.id")
    List<SubTag> findAllOrderById();
}

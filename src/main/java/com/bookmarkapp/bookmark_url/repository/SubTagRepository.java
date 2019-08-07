package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.SubTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public interface SubTagRepository extends JpaRepository<SubTag, Integer> {
    @Query("SELECT x FROM SubTag x ORDER BY x.id")
    List<SubTag> findAllOrderById();

    @Query("SELECT x FROM SubTag x WHERE x.id IN :ids")
    Set<SubTag> findSubTagsByIds(@Param("ids") Integer[] ids);
}

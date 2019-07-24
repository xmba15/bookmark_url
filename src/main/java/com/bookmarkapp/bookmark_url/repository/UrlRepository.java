package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    @Query("SELECT x FROM Url x ORDER BY x.id")
    List<Url> findAllOrderById();
}

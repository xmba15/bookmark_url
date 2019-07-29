package com.bookmarkapp.bookmark_url.repository;

import com.bookmarkapp.bookmark_url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    @Query("SELECT x FROM Url x ORDER BY x.id")
    List<Url> findAllOrderById();

    @Query("SELECT x FROM Url x WHERE x.address = :address")
    Optional<Url> findOneByAddress(@Param("address") String address);
}

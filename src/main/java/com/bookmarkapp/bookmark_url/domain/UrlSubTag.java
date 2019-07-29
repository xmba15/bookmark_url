package com.bookmarkapp.bookmark_url.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@Table(name = "url_subtag")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UrlSubTag {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "subtag_id")
    private SubTag subTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "url_id")
    private Url url;
}

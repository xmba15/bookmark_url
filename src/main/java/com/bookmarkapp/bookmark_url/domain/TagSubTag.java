package com.bookmarkapp.bookmark_url.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tag_subtag")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TagSubTag {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "tag_id")
    private Tag tag;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "subtag_id")
    private SubTag subTag;
}

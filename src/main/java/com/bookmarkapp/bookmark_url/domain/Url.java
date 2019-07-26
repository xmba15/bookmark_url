package com.bookmarkapp.bookmark_url.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "urls")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "address")
    private String address;

    @Column(nullable = false, name = "description")
    private String description;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @ManyToMany
    @JoinTable(
        name="url_tag",
        joinColumns = @JoinColumn(name = "url_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @ManyToMany
    @JoinTable(
        name="url_subtag",
        joinColumns = @JoinColumn(name = "url_id"),
        inverseJoinColumns = @JoinColumn(name = "subtag_id"))
    private Set<SubTag> subtags;
}

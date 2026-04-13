package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String content;
    @Column(length = 8, nullable = false)
    private String authorName;
    @Column(length = 8, nullable = false)
    private String password;

    public Comment(String content, String authorName, String password){
        this.content = content;
        this.authorName = authorName;
        this.password = password;
    }

}

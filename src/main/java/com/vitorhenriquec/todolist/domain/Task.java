package com.vitorhenriquec.todolist.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "task")
@EqualsAndHashCode(of = {"id"})
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean done = Boolean.FALSE;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dateCreated = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdated = new Date();

    @Version
    private Long version = 0L;

    public static String[] ignoreProperties = {"id", "dateCreated", "lastUpdated", "version"};
}

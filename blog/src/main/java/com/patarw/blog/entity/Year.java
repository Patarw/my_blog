package com.patarw.blog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "year")
public class Year {
    @Id
    @GeneratedValue
    private Long id;
    private String year;
}

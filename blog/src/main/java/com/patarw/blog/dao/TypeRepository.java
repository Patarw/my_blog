package com.patarw.blog.dao;

import com.patarw.blog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findTypeById(Long id);
}

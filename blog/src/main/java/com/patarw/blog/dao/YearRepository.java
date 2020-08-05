package com.patarw.blog.dao;

import com.patarw.blog.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year,Long> {
}

package com.patarw.blog.dao;

import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    Blog getBlogById(Long id);
    List<Blog> getBlogByYearOrderByUpdateTimeDesc(String year);
    List<Blog> getBlogByTypeOrderByUpdateTimeDesc(Type type);
}

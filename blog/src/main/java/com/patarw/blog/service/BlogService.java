package com.patarw.blog.service;

import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable,Blog blog);

    Page<Blog> listBlog(Pageable pageable);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    List<Blog> listBlogByTypeId(Long id);

    Blog getAndConvert(Long id);

    List<Blog> listBlogByYear(String year);

    void addView(Blog blog);
}

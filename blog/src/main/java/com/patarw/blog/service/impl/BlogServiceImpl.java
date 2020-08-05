package com.patarw.blog.service.impl;

import com.patarw.blog.dao.BlogRepository;
import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.Type;
import com.patarw.blog.exception.NotFoundException;
import com.patarw.blog.service.BlogService;
import com.patarw.blog.service.TypeService;
import com.patarw.blog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private TypeService typeService;

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getBlogById(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer i = calendar.get(Calendar.MONTH);
        Integer j = calendar.get(Calendar.DAY_OF_MONTH);
        Integer k = calendar.get(Calendar.YEAR);
        String month;
        if (i<10) {
            month = "0" + i;
        }else {
            month = "" + i;
        }
        String day = ""+ j;
        String year = ""+ k;

        blog.setDay(day);
        blog.setMonth(month);
        blog.setYear(year);
        if (blog.getPublished() == null){
            blog.setPublished(false);
        }
        if (blog.getRecommended() == null){
            blog.setRecommended(false);
        }
        blog.setViews(0);
        return blogRepository.save(blog);
    }
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        blog.setUpdateTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer i = calendar.get(Calendar.MONTH);
        Integer j = calendar.get(Calendar.DAY_OF_MONTH);
        Integer k = calendar.get(Calendar.YEAR);
        String month;
        if (i<10) {
            month = "0" + i;
        }else {
            month = "" + i;
        }
        String day = ""+ j;
        String year = ""+ k;

        blog.setDay(day);
        blog.setMonth(month);
        blog.setYear(year);

        if (blog.getPublished() == null){
            blog.setPublished(false);
        }
        if (blog.getRecommended() == null){
            blog.setRecommended(false);
        }
        Blog b = blogRepository.getBlogById(id);
        if (b == null){
            throw new NotFoundException("博客不存在");
        }
        blog.setCreateTime(b.getCreateTime());
        blog.setViews(b.getViews());
        BeanUtils.copyProperties(blog,b);
        return  blogRepository.save(b);
    }
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> listBlogByTypeId(Long id) {
        Type type = typeService.getType(id);
        if (type == null){
            throw new NotFoundException("类目不存在");
        }
        return blogRepository.getBlogByTypeOrderByUpdateTimeDesc(type);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getBlogById(id);
        if (blog == null){
            throw new NotFoundException("资源不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Override
    public List<Blog> listBlogByYear(String year) {
        return blogRepository.getBlogByYearOrderByUpdateTimeDesc(year);
    }

    @Override
    public void addView(Blog blog) {
        blog.setViews(blog.getViews()+1);
        blogRepository.save(blog);
    }
}

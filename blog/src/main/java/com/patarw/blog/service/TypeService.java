package com.patarw.blog.service;

import com.patarw.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TypeService {
    Type insertType(Type type);

    void deleteType(Long id);

    Type updateType(Long id,Type type);

    Type getType(Long id);

    Page<Type> listTypeByPage(Pageable pageable);

    List<Type> listType();
}

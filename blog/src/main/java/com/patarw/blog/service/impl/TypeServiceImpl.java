package com.patarw.blog.service.impl;

import com.patarw.blog.dao.TypeRepository;
import com.patarw.blog.entity.Type;
import com.patarw.blog.exception.NotFoundException;
import com.patarw.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type insertType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findTypeById(id);
        if (t == null){
            throw new NotFoundException("类目不存在");
        }
        BeanUtils.copyProperties(type,t);
        return  typeRepository.save(t);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.findTypeById(id);
    }

    @Transactional
    @Override
    public Page<Type> listTypeByPage(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }
}

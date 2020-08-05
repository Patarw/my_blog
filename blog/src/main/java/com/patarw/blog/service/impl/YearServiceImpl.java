package com.patarw.blog.service.impl;

import com.patarw.blog.dao.YearRepository;
import com.patarw.blog.entity.Year;
import com.patarw.blog.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearServiceImpl implements YearService {

    @Autowired
    private YearRepository yearRepository;

    @Override
    public List<Year> listYear() {
        return yearRepository.findAll();
    }
}

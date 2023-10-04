package com.example.liquidbase.service;

import com.example.liquidbase.repository.JDBCRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JDBCServiceImpl implements JDBCService{
    private final JDBCRepositoryImpl repository;

    @Override
    public List<String> getProductNameList(String name) {
        return repository.getProductName(name);
    }

}

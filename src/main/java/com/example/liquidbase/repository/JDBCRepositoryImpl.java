package com.example.liquidbase.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JDBCRepositoryImpl implements JDBCRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String script = read("ScriptForNames.sql");

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getProductName(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        List<String> productLis = namedParameterJdbcTemplate.queryForList(script, map, String.class);
        return productLis;
    }
}

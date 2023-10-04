package com.example.liquidbase.repository;

import com.example.liquidbase.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JDBCRepositoryImpl implements JDBCRepository {
    @Autowired
    DataSource dataSource;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
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
        List<String> productLis = namedParameterJdbcTemplate.query(script, map, (rs, rowNum) -> {
            String productName = rs.getString("product_name");
            return (new Product(productName)).toString();

        });
        return productLis;
    }
}

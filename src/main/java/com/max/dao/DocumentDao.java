package com.max.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DocumentDao {
	
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    //查询指定字段的DocumentList
    public List<Map<String, Object>> queryDocumentList(String sql) {
    	System.out.println("the entire sql state is :" + sql);
    	List<Map<String,Object>> documentList = jdbcTemplate.queryForList(sql);
    	return documentList;
    }
    

}

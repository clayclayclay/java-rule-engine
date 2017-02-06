package com.max.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.max.entity.RuleEntity;


public interface RuleEntityRepository extends JpaRepository<RuleEntity, Integer> {
	
	@SuppressWarnings("unchecked")
	public RuleEntity save(RuleEntity ruleEntity);

}

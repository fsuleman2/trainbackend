package com.train.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

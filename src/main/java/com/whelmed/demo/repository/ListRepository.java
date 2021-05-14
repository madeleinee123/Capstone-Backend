package com.whelmed.demo.repository;

import com.whelmed.demo.model.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<List, Long> {
    List findByName(String categoryName);
}

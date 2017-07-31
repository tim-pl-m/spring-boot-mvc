package com.projektpool.example.dao.jpa;

import com.projektpool.example.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    // Project findProjectByCustomer(String customer);
    Page findAll(Pageable pageable);
}

package io.github.nuto17.financetracker.repository;

import io.github.nuto17.financetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Boolean existsCategoryById(Long id);
}

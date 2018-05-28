/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.repository;

import com.example.golfclub.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author max19
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByType(String type);
}

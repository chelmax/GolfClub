/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.repository;

import com.example.golfclub.entity.Field;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author max19
 */
public interface FieldRepository extends JpaRepository<Field, Integer> {
    Optional<Field> findByFieldName(String fieldName);
}

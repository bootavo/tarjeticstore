package com.example.bucket.repository;

import com.example.bucket.model.Promotion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PromotionRepository extends CrudRepository<Promotion, Integer> {

}

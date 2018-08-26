package com.example.bucket.repository;

import com.example.bucket.model.Inventory;
import com.example.bucket.model.User;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {



}

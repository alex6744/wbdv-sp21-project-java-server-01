package com.example.jwtdemo2.repository;

import com.example.jwtdemo2.models.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends JpaRepository<Creator,Long> {
}

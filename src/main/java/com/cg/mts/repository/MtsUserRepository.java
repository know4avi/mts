package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.MtsUser;

@Repository
public interface MtsUserRepository extends JpaRepository<MtsUser, String> {

}

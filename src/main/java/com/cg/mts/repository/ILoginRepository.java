package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.AdmissionCommiteeMember;

@Repository
public interface ILoginRepository extends JpaRepository<AdmissionCommiteeMember, Integer>{

}

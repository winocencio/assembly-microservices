package com.winocencio.assembly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winocencio.assembly.model.Docket;

@Repository
public interface DocketRepository extends JpaRepository<Docket, Integer> {

}

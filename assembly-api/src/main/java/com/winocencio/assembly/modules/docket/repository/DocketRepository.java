package com.winocencio.assembly.modules.docket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winocencio.assembly.modules.docket.model.Docket;

@Repository
public interface DocketRepository extends JpaRepository<Docket, Integer> {

}

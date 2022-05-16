package com.winocencio.assembly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winocencio.assembly.model.Docket;
import com.winocencio.assembly.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

	public Optional<Session> findByDocket(Docket docket);
}

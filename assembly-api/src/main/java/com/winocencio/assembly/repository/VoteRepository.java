package com.winocencio.assembly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winocencio.assembly.model.Session;
import com.winocencio.assembly.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

	public List<Vote> findBySessionAndAssociateCpf(Session session,String associateCpf);
}

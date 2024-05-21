package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Closet;

@Repository
public interface ClosetRepository extends JpaRepository<Closet, Integer> {
	public Optional<Closet> findById(int id);
	public List<Closet> findByName(String key);
	public List<Closet> findByNameLike(String key);
}

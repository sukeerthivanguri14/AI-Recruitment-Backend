package com.airecruitment.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airecruitment.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

	List<Resume> findByEmail(String email);

}
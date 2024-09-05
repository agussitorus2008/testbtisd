package com.helloword.demo_project.repository;

import com.helloword.demo_project.model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
}
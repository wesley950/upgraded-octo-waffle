package com.example.chan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chan.models.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {

}

package com.example.chan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chan.models.ThreadModel;

@Repository
public interface ThreadRepository extends JpaRepository<ThreadModel, Long> {

}

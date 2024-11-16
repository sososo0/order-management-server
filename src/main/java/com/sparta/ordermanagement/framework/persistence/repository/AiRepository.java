package com.sparta.ordermanagement.framework.persistence.repository;

import com.sparta.ordermanagement.framework.persistence.entity.ai.AiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiRepository extends JpaRepository<AiEntity, Long> {

}

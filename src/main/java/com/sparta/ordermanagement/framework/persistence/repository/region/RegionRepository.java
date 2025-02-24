package com.sparta.ordermanagement.framework.persistence.repository.region;

import com.sparta.ordermanagement.framework.persistence.entity.region.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

}

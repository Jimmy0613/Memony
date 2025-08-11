package com.memony.repository.monster;

import com.memony.entity.monster.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterJpaRepository extends JpaRepository<Monster, Long> {

}

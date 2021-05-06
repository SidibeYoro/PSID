package com.project.givemehand.repository;

import com.project.givemehand.models.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Le repository de la demande, elle herite de la bibliothèque JPA
 */
public interface RequestRepository extends JpaRepository<Demande,Long> {

}

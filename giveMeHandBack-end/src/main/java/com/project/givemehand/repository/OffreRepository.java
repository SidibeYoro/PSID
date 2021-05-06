package com.project.givemehand.repository;

import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
/**
 * Le repository de l'offre, elle herite de JPA
 */
public interface OffreRepository extends JpaRepository<Offre,Long>
{


}

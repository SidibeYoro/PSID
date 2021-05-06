package com.project.givemehand.interfaces;

import com.project.givemehand.models.entity.Demande;
import com.project.givemehand.models.entity.Offre;

import java.util.List;

public interface IDemande {
    public List<Demande> getDemandesByOffre(Offre off);
}

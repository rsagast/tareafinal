package com.intecap.tareaFinal.model.dao;

import com.intecap.tareaFinal.model.FabricanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFabricanteDAO extends JpaRepository<FabricanteEntity,Long> {
}

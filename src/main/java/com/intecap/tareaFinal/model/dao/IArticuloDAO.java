package com.intecap.tareaFinal.model.dao;

import com.intecap.tareaFinal.model.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticuloDAO extends JpaRepository<ArticuloEntity,Long> {
}

package com.generation.farmacia.repository;

import com.generation.farmacia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface categoriaRepository extends JpaRepository <Categoria,Long> {

    public List<Categoria> findAllByTipoContainingIgnoreCase(@Param("tipo")String tipo);


}

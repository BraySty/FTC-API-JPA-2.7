package com.ftc.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftc.jpa.entitys.Registro;

public interface RegistroRepository extends JpaRepository<Registro,Integer>{

    public List<Registro> findFirstByOrderByIdRegistroDesc();

}

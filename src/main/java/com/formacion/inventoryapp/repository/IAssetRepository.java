package com.formacion.inventoryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacion.inventoryapp.entity.AssetEntity;

@Repository
public interface IAssetRepository extends JpaRepository<AssetEntity, Long> {
}
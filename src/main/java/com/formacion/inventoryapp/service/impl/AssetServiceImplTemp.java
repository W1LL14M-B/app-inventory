package com.formacion.inventoryapp.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacion.inventoryapp.entity.AssetEntity;
import com.formacion.inventoryapp.repository.IAssetRepository;

@Service
public class AssetServiceImplTemp {

	private static final double DEPRECIATION_RATE = 0.04;

    @Autowired
    private IAssetRepository iassetRepository;

    public List<AssetEntity> getAllAssets() {
        return iassetRepository.findAll();
    }

    public AssetEntity getAssetById(Long id) {
        return iassetRepository.findById(id).orElse(null);
    }

    public AssetEntity saveAsset(AssetEntity assetEntity) {
        return iassetRepository.save(assetEntity);
    }

    public AssetEntity updateAsset(Long id, AssetEntity assetDetails) {
    	AssetEntity assetEntity = iassetRepository.findById(id).orElse(null);
        if (assetEntity != null) {
        	assetEntity.setName(assetDetails.getName());
        	assetEntity.setDescription(assetDetails.getDescription());
        	assetEntity.setPurchaseDate(assetDetails.getPurchaseDate());
        	assetEntity.setPurchaseValue(assetDetails.getPurchaseValue());
        	assetEntity.setSerialNumber(assetDetails.getSerialNumber());
            return iassetRepository.save(assetEntity);
        }
        return null;
    }

    public void deleteAsset(Long id) {
        iassetRepository.deleteById(id);
    }

    public double calculateDepreciation(AssetEntity assetEntity) {
        long years = ChronoUnit.YEARS.between(assetEntity.getPurchaseDate(), LocalDate.now());
        return assetEntity.getPurchaseValue() * Math.pow((1 - DEPRECIATION_RATE), years);
    }
}

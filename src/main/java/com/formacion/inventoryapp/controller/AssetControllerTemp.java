package com.formacion.inventoryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacion.inventoryapp.entity.AssetEntity;
import com.formacion.inventoryapp.service.impl.AssetServiceImplTemp;

public class AssetControllerTemp {

	@Autowired
    private AssetServiceImplTemp assetServiceImpl;

    @GetMapping
   // @Operation(summary = "Get all assets")
    public List<AssetEntity> getAllAssets() {
        return assetServiceImpl.getAllAssets();
    }

    @GetMapping("/{id}")
    //@Operation(summary = "Get an asset by ID")
    public ResponseEntity<AssetEntity> getAssetById(@PathVariable Long id) {
    	AssetEntity assetEntity = assetServiceImpl.getAssetById(id);
        return assetEntity != null ? ResponseEntity.ok(assetEntity) : ResponseEntity.notFound().build();
    }

    @PostMapping
    //@Operation(summary = "Create a new asset")
    public AssetEntity createAsset(/*@Valid*/ @RequestBody AssetEntity assetEntity) {
        return assetServiceImpl.saveAsset(assetEntity);
    }

    @PutMapping("/{id}")
    //@Operation(summary = "Update an existing asset")
    public ResponseEntity<AssetEntity> updateAsset(@PathVariable Long id, /*@Valid*/ @RequestBody AssetEntity assetDetails) {
    	AssetEntity updatedAsset = assetServiceImpl.updateAsset(id, assetDetails);
        return updatedAsset != null ? ResponseEntity.ok(updatedAsset) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    //@Operation(summary = "Delete an asset")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
    	assetServiceImpl.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/depreciation")
    //@Operation(summary = "Calculate depreciation for an asset")
    public ResponseEntity<Double> calculateDepreciation(@PathVariable Long id) {
    	AssetEntity assetEntity = assetServiceImpl.getAssetById(id);
        if (assetEntity != null) {
            double depreciationValue = assetServiceImpl.calculateDepreciation(assetEntity);
            return ResponseEntity.ok(depreciationValue);
        }
        return ResponseEntity.notFound().build();
    }
}
	


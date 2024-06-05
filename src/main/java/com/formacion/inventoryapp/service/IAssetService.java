package com.formacion.inventoryapp.service;

import com.formacion.inventoryapp.dto.request.AssetDTO;
import com.formacion.inventoryapp.dto.response.GenericoDataRsDTO;
import com.formacion.inventoryapp.dto.response.GenericoListaRsDTO;
import com.formacion.inventoryapp.dto.response.GenericoRsDTO;

public interface IAssetService {
	GenericoDataRsDTO<AssetDTO> registerAsset(AssetDTO assetDTO);
    GenericoDataRsDTO<AssetDTO> updateAsset(AssetDTO assetDTO);
    GenericoRsDTO deleteAsset(AssetDTO assetDTO);
    GenericoDataRsDTO<AssetDTO> getAssetById(AssetDTO assetDTO);
    
    /**
     * Metodo que sencarga de listar todos lo equipos activos.
     * 
     * @return
     * @author Willy
     */
    GenericoListaRsDTO<AssetDTO> getAllAssets();
    
    Double calculateDepreciation(Long id, Double depreciationRate);
}

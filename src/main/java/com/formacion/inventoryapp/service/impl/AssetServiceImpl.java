package com.formacion.inventoryapp.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacion.inventoryapp.dto.request.AssetDTO;
import com.formacion.inventoryapp.dto.response.GenericoDataRsDTO;
import com.formacion.inventoryapp.dto.response.GenericoListaRsDTO;
import com.formacion.inventoryapp.dto.response.GenericoRsDTO;
import com.formacion.inventoryapp.entity.AssetEntity;
import com.formacion.inventoryapp.exception.GeneralException;
import com.formacion.inventoryapp.repository.IAssetRepository;
import com.formacion.inventoryapp.service.IAssetService;

@Service
public class AssetServiceImpl implements IAssetService {

	@Autowired
    private IAssetRepository assetRepository;
	
	@Override
	public GenericoDataRsDTO<AssetDTO> registerAsset(AssetDTO assetDTO) {
		AssetEntity assetEntity = new AssetEntity();
        assetEntity.setSerialNumber(assetDTO.getSerialNumber());
        assetEntity.setName(assetDTO.getName());
        assetEntity.setDescription(assetDTO.getDescription());
        assetEntity.setPurchaseDate(assetDTO.getPurchaseDate());
        assetEntity.setPurchaseValue(assetDTO.getPurchaseValue());
        
        AssetEntity savedAssetEntity = AssetDTO.convAssetDTOToEntity(assetDTO, assetEntity);
        AssetEntity savedAsset = assetRepository.save(savedAssetEntity);
        
        AssetDTO savedAssetDTO = AssetDTO.convAssetEntityToDTO(savedAsset, assetDTO);
        
        GenericoDataRsDTO<AssetDTO> response = new GenericoDataRsDTO<>();
        response.setDatoGenerico(savedAssetDTO);
        response.setExitoso(true);
        return response;
	}

	/*@Override
	public GenericoDataRsDTO<AssetDTO> updateAsset(AssetDTO assetDTO) {
		Optional<AssetEntity> assetOptional = assetRepository.findById(assetRequestDto.getId());
        if (assetOptional.isPresent()) {
            AssetEntity assetEntity = assetOptional.get();
            assetEntity.setSerialNumber(assetRequestDto.getSerialNumber());
            assetEntity.setName(assetRequestDto.getName());
            assetEntity.setDescription(assetRequestDto.getDescription());
            assetEntity.setPurchaseDate(assetRequestDto.getPurchaseDate());
            assetEntity.setPurchaseValue(assetRequestDto.getPurchaseValue());
            
            AssetEntity updatedAsset = assetRepository.save(assetEntity);
            
            GenericoDataRsDTO<AssetEntity> response = new GenericoDataRsDTO<>();
            response.setDatoGenerico(updatedAsset);
            response.setExitoso(true);
            return response;
        } else {
            GenericoDataRsDTO<AssetEntity> response = new GenericoDataRsDTO<>();
            response.setExitoso(false);
            response.setDescripcionError("Asset not found");
            return response;
        }
	}*/

	/*@Override
	public GenericoRsDTO deleteAsset(AssetDTO assetDTO) {
		assetRepository.deleteById(id);
        GenericoRsDTO response = new GenericoRsDTO();
        response.setExitoso(true);
        return response;
	}*/

	/*@Override
	public GenericoDataRsDTO<AssetDTO> getAssetById(AssetDTO assetDTO) {
		Optional<AssetEntity> assetOptional = assetRepository.findById(id);
        GenericoDataRsDTO<AssetEntity> response = new GenericoDataRsDTO<>();
        if (assetOptional.isPresent()) {
            response.setDatoGenerico(assetOptional.get());
            response.setExitoso(true);
        } else {
            response.setExitoso(false);
            response.setDescripcionError("Asset not found");
        }
        return response;
	}*/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericoListaRsDTO<AssetDTO> getAllAssets() { // Se declara el método getAllAssets que devuelve un objeto de tipo GenericoListaRsDTO<AssetDTO>.
		// Se obtiene una lista de todas las entidades AssetEntity desde el repositorio assetRepository.
		List<AssetEntity> assetsEntity = assetRepository.findAll();
		// Se convierte la lista de entidades AssetEntity a una lista de objetos AssetDTO.
		List<AssetDTO> assestDTO = assetsEntity.stream() // Utilizando el método stream() de la lista assetsEntity, se crea un flujo (stream) de elementos.
	            .map(assetEntity -> AssetDTO.convAssetEntityToDTO(assetEntity, new AssetDTO())) // El método map() aplica la función de conversión convAssetEntityToDTO a cada elemento del flujo.
	            .collect(Collectors.toList()); // La función convAssetEntityToDTO toma un AssetEntity y lo convierte en un AssetDTO.
        GenericoListaRsDTO<AssetDTO> response = new GenericoListaRsDTO<>(); // Se crea una nueva instancia de GenericoListaRsDTO<AssetDTO> llamada response. Esta instancia se utilizará para encapsular la lista de DTOs y otros metadatos de la respuesta.
        response.setListaDatos(assestDTO); // Se establece la lista de AssetDTOs (assestDTO) en el objeto response usando el método setListaDatos().
        response.setExitoso(true); // Se establece el estado exitoso de la respuesta a true usando el método setExitoso().
        return response; // Se devuelve el objeto response, que contiene la lista de AssetDTOs y el estado exitoso.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double calculateDepreciation(Long id, Double depreciationRate) {
		Optional<AssetEntity> assetOptional = assetRepository.findById(id);
        if (assetOptional.isPresent()) {
            AssetEntity asset = assetOptional.get();
            int years = Period.between(asset.getPurchaseDate(), LocalDate.now()).getYears();
            Double depreciation = asset.getPurchaseValue() * Math.pow((1 - depreciationRate / 100), years);
            return asset.getPurchaseValue() - depreciation;
            //TODO: hay que guardar el valor del cálculo depreciado en bd.
        } else {
            throw new GeneralException("Asset not found", "");
        }
	}

	@Override
	public GenericoDataRsDTO<AssetDTO> updateAsset(AssetDTO assetDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericoRsDTO deleteAsset(AssetDTO assetDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericoDataRsDTO<AssetDTO> getAssetById(AssetDTO assetDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}

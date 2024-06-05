package com.formacion.inventoryapp.dto.request;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.formacion.inventoryapp.entity.AssetEntity;

//@Data
public class AssetDTO {

    private Long id;

    private String serialNumber;

    private String name;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO", timezone = "America/Bogota")
    private LocalDate purchaseDate;

    private Double purchaseValue;
    
    private Double calculateDepreciationValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(Double purchaseValue) {
		this.purchaseValue = purchaseValue;
	}
	
	public Double getCalculateDepreciationValue() {
		return calculateDepreciationValue;
	}

	public void setCalculateDepreciationValue(Double calculateDepreciationValue) {
		this.calculateDepreciationValue = calculateDepreciationValue;
	}

	/**
	 * Método que se encarga de convertir AssetEntity  a  AssetDTO
	 * 
	 * @param parAssetEntity
	 * @param parAssetDto
	 * @return
	 * @author Willy
	 */
	public static AssetDTO convAssetEntityToDTO(AssetEntity parAssetEntity, AssetDTO parAssetDto) {
		if (parAssetDto == null) {
			parAssetDto = new AssetDTO();
	    }
	    BeanUtils.copyProperties(parAssetEntity, parAssetDto);
	    return parAssetDto;
	}
	
	public static AssetEntity convAssetDTOToEntity(AssetDTO parAssetDto, AssetEntity parAssetEntity) {
		if (parAssetEntity == null) {
			parAssetEntity = new AssetEntity();
	    }
	    BeanUtils.copyProperties(parAssetDto, parAssetEntity);
	    return parAssetEntity;
	}
}

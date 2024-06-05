package com.formacion.inventoryapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ASSET")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    //@NotNull
    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    //@NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    //@NotNull
    @Column(name = "PURCHASE_DATE")
    private LocalDate purchaseDate;

    //@NotNull
    @Column(name = "PURCHASE_VALUE")
    private Double purchaseValue;
    
    @Column(name = "CALCULATE_DEPRECIATION_VALUE")
    private Double calculateDepreciationValue;

    public AssetEntity() {}

    // Getters and Setters

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
}


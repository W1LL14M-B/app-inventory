package com.formacion.inventoryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.inventoryapp.dto.request.AssetDTO;
import com.formacion.inventoryapp.dto.response.GenericoDataRsDTO;
import com.formacion.inventoryapp.dto.response.GenericoListaRsDTO;
import com.formacion.inventoryapp.exception.GeneralException;
import com.formacion.inventoryapp.service.IAssetService;
import com.formacion.inventoryapp.utility.Constants;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/technical-asset")
public class AssetController {

	@Autowired
	private IAssetService assetService;
	
	/**
	 * Método que se encarga de guardar o registrar un equipo/activo
	 * 
	 * @param assetDTO
	 * @return
	 * @author Willy
	 */
	@PostMapping(value = "/save-asset", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericoDataRsDTO<AssetDTO>> saveAsset(
			@RequestBody AssetDTO assetDTO) {
		GenericoDataRsDTO<AssetDTO> responseAsset = assetService.registerAsset(assetDTO);
		if (responseAsset.getTipoError() != null) {
			return new ResponseEntity<>(responseAsset, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (responseAsset.getDatoGenerico() == null) {
				responseAsset.setRespuesta(Boolean.FALSE, Constants.TIPO_MENSAJE_ADVERTENCIA,
						Constants.CONSTANTE_MENSAJE_NO_HAY_REGISTROS);
				return new ResponseEntity<>(responseAsset, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(responseAsset, HttpStatus.OK);
		}
	}

	
    /**
     * Método que se encarga de calcular la depreciación. Se calcula la perdida del 4 % del valor del equipo cada año este porcentaje puede variar segun reglas fiscales.
     * 
     * @param id
     * @param depreciationRate
     * @return
     * @author Willy
     */
    @GetMapping(value = "/calculate-depreciation", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<GenericoDataRsDTO<Double>> calculateDepreciation(
            @RequestParam Long id,
            @RequestParam Double depreciationRate) {
        try {
            Double depreciationValue = assetService.calculateDepreciation(id, depreciationRate);
            GenericoDataRsDTO<Double> response = new GenericoDataRsDTO<>();
            response.setDatoGenerico(depreciationValue);
            response.setExitoso(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (GeneralException e) {
            GenericoDataRsDTO<Double> response = new GenericoDataRsDTO<>();
            response.setExitoso(false);
            response.setDescripcionError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping(value = "/get-asset-all")
	public ResponseEntity<GenericoListaRsDTO<AssetDTO>> getAssetsAll() {
		GenericoListaRsDTO<AssetDTO> responseGetAll = assetService.getAllAssets();
		if (responseGetAll.getTipoError() != null) {
			return new ResponseEntity<>(responseGetAll, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (responseGetAll.getListaDatos() == null || responseGetAll.getListaDatos().isEmpty()) {
				responseGetAll.setRespuesta(Boolean.FALSE, Constants.TIPO_MENSAJE_ADVERTENCIA,
						Constants.CONSTANTE_MENSAJE_NO_HAY_REGISTROS);
				return new ResponseEntity<>(responseGetAll, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
		}
	}
	
	/*@PostMapping(value = "/get-asset-id")
	public ResponseEntity<GenericoDataRsDTO<AssetDTO>> getAssetId(
			@RequestBody AssetDTO assetDTO o @RequestParam Long id) {
		GenericoListaRsDTO<AccionUsuarioDTO> respuestaListaAccionUsuario = reporteNavegacionUsuarioCudService.consultarNavegacion(consulta);
		if (respuestaListaAccionUsuario.getTipoError() != null) {
			return new ResponseEntity<>(respuestaListaAccionUsuario, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (respuestaListaAccionUsuario.getListaDatos() == null || respuestaListaAccionUsuario.getListaDatos().isEmpty()) {
				respuestaListaAccionUsuario.setRespuesta(Boolean.FALSE, Constantes.TIPO_MENSAJE_ADVERTENCIA,
						Constantes.CONSTANTE_MENSAJE_NO_HAY_REGISTROS);
				return new ResponseEntity<>(respuestaListaAccionUsuario, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(respuestaListaAccionUsuario, HttpStatus.OK);
		}
	}*/
	
	/*@PostMapping(value = "/eliminar-asset", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericoRsDTO> eliminarContacto(@RequestBody ConsultarSolicitudInformacionRqDTO consulta) {
		GenericoRsDTO respuestaEliminar = solicitudInformacionCudService.eliminarSolicitudInformacion(consulta);
		if (respuestaEliminar.getTipoError() != null) {
			return new ResponseEntity<>(respuestaEliminar, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(respuestaEliminar, HttpStatus.OK);
		}
	}*/
}

package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/medicamento")
//@CrossOrigin(origins = "http:/localhost:42100")
public class MedicamentoController {

	@Autowired
	private MedicamentoService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> lista(){
		List<Medicamento> lista = service.listaMedicamento();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> inserta (@RequestBody Medicamento obj){
		Map<String, Object> Salida = new HashMap<>();
		try {
			Medicamento objSalida = service.insertaActualiza(obj);
			if (objSalida ==null) {
				Salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
				
			} else {
				Salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
	
	return ResponseEntity.ok(Salida);
	}
}

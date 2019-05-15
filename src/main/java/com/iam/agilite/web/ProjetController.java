package com.iam.agilite.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iam.agilite.entity.Projet;
import com.iam.agilite.execption.ProjetException;
import com.iam.agilite.repository.MapValidationErrorService;
import com.iam.agilite.repository.ProjetRepository;
import com.iam.agilite.repository.ProjetService;

@RestController
@RequestMapping("/api/projet")
public class ProjetController {

	// injection du repostory dans le contrroller
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	// creation d'un projet
	/*
	 * public Projet saveOrUpdate(Projet projet) { try {
	 * projet.setCode(projet.getCode().toUpperCase()); return
	 * projetRepository.save(projet); } catch (Exception e) { // TODO: handle
	 * exception throw new
	 * ProjetException("code Projet:"+projet.getCode().toUpperCase()+"existe deja !"
	 * ); } }
	 */
	@PostMapping("/add")
	public ResponseEntity<?> createNewProjet(@Valid @RequestBody Projet p,BindingResult result) {
		ResponseEntity<?> erroMap = mapValidationErrorService.mapValidationService(result);
		 boolean codeVerify = projetRepository.existsByCode(p.getCode());
				
			if(erroMap != null)
			{
	
			
				return erroMap;
	
			}else {
			
			    	Projet projet = projetRepository.save(p);
					return new ResponseEntity<Projet>(projet, HttpStatus.CREATED);
			}
			
			
		
		
	
		
	}

	// methode pour afficher les projets
	@GetMapping("/liste")
	public ResponseEntity<List<Projet>> listesProjet()

	{
		List<Projet> listeProojet = (List<Projet>) projetRepository.findAll();
		return new ResponseEntity<>(listeProojet, HttpStatus.OK);
	}

	
	  
	  //fin project by code
	  @GetMapping("/{code}")
	  public ResponseEntity<?> getProjetByCode(@PathVariable String code)
	  { 
		 Projet projet = projetRepository.findByCode(code);
		 return new ResponseEntity<>(projet,HttpStatus.OK); 
	  }
	 
	  //delete fonction
	  @DeleteMapping("/{code}")
	  public ResponseEntity<?> deleteProject(@PathVariable String code)
	  {
		 Projet projet = projetRepository.findByCode(code);
		 projetRepository.delete(projet);
		  
		 return new ResponseEntity<>("projet supprimer avec succes",HttpStatus.OK); 
		  
	  }

}

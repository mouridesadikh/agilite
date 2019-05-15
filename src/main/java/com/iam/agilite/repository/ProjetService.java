package com.iam.agilite.repository;

import org.springframework.stereotype.Service;

import com.iam.agilite.entity.Projet;
@Service
public class ProjetService {

	
	public boolean verfyDate(Projet p)
	{
		boolean verfy =false;
		if(p.getDateFin()!=null && p.getDateDebut()!=null)
		{
			if(p.getDateFin().after(p.getDateDebut()))
			{
				verfy = true;
			}else {
				verfy = false;
			}
		}
		return verfy;
	}
}

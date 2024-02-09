package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IMateriaRepository;
import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;

@Service
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public List<MateriaTO> buscarIdEstud(int id) {
		// TODO Auto-generated method stub
		List<Materia> list = this.materiaRepository.selectIdEstudent(id);

		List<MateriaTO> listToFinal = new ArrayList<>();

		for (Materia mat : list) {
			listToFinal.add(this.conversion(mat));
		}

		return listToFinal;
	}

	private MateriaTO conversion(Materia materia) {
		MateriaTO mTo = new MateriaTO();
		mTo.setId(materia.getId());
		mTo.setNombre(materia.getNombre());
		mTo.setCreditos(materia.getCreditos());
		return mTo;
	}

}

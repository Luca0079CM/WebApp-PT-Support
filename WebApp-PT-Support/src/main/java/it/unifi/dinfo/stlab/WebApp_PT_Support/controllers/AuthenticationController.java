package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.CustomerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.PersonalTrainerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.utils.JwtUtil;

@ApplicationScoped
public class AuthenticationController {

    @Inject
    private PersonalTrainerDao ptDao;
    @Inject
    PersonalTrainerMapper ptMapper;

    @Inject
    private JwtUtil jwtUtil;

    public String authenticate(String email, String password) {
        PersonalTrainer pt = ptDao.findByEmail(email);
        if (pt != null) {
        	System.out.println("token is being generated in AuthenticationController");
            // Genera un token JWT valido
            return jwtUtil.generateToken(pt.getEmail());
        }
        System.out.println("pt is null in AuthenticationController");

        return null;
    }
    
    public PersonalTrainerDTO register(PersonalTrainerDTO ptDTO) {
    	PersonalTrainer pt = new PersonalTrainer();
    	pt.setId(ptDTO.getId());
    	pt.setName(ptDTO.getName());
    	pt.setSurname(ptDTO.getSurname());
    	pt.setEmail(ptDTO.getEmail());
    	pt.setDateOfBirth(LocalDate.parse(ptDTO.getDateOfBirth()));
    	pt.setPassword(ptDTO.getPassword());
//    	pt.setWorkoutProgramList(null);
    	ptDao.save(pt);
		return ptMapper.toDTO(pt);
	}

    private boolean passwordMatches(PersonalTrainer pt, String password) {
        if(pt.getPassword() == password)
        	return true;
        else
        	return false;
//        return BCrypt.checkpw(password, pt.getPassword());
    }
}


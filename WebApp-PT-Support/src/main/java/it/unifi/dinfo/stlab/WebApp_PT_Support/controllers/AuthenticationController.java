package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
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
    private CustomerDao cDao;
    @Inject
    private PersonalTrainerMapper ptMapper;
    @Inject
    private CustomerMapper cMapper;

    @Inject
    private JwtUtil jwtUtil;

    public String authenticate(String email, String password) {
        PersonalTrainer pt = ptDao.findByEmail(email);
        if (pt != null || passwordMatches(pt, password)) {
        	System.out.println("token is being generated in AuthenticationController");
            // Genera un token JWT valido
            return jwtUtil.generateToken(pt.getEmail());
        }
        System.out.println("pt is null in AuthenticationController or password is wrong");
        return null;
    }
    
    public String authenticateCustomer(String email, String password) {
        Customer c = cDao.findByEmail(email);
        if (c != null || customerPasswordMatches(c, password)) {
        	System.out.println("token is being generated in AuthenticationController");
            // Genera un token JWT valido
            return jwtUtil.generateToken(c.getEmail());
        }
        System.out.println("customer is null in AuthenticationController or password is wrong");
        return null;
    }
    
    public PersonalTrainerDTO register(PersonalTrainerDTO ptDTO) {
    	PersonalTrainer pt = ptMapper.toEntity(ptDTO);
    	ptDao.save(pt);
		return ptMapper.toDTO(pt);
	}
    
    public CustomerDTO registerCustomer(CustomerDTO cDTO) {
    	Customer c = cMapper.toEntity(cDTO);
    	c.setPersonalTrainer(ptDao.findById(40L));
    	cDao.save(c);
		return cMapper.toDTO(c);
	}

    private boolean passwordMatches(PersonalTrainer pt, String password) {
        if(pt.getPassword() == password)
        	return true;
        else
        	return false;
//        return BCrypt.checkpw(password, pt.getPassword());
    }
    
    private boolean customerPasswordMatches(Customer c, String password) {
        if(c.getPassword() == password)
        	return true;
        else
        	return false;
//        return BCrypt.checkpw(password, pt.getPassword());
    }
}


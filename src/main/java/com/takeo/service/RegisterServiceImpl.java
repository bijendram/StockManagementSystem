package com.takeo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.takeo.Entity.Register;
import com.takeo.repo.RegisterRepo;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepo registerRepo;

	@Override
	public boolean addUser(Register register) {
		if (getUserByEmail(register.getEmail()) == null) {
			registerRepo.insert(register);
			return true;

		}

		return false;
	}

	@Override
	public Register verifyUser(Register register) {
		if (getUserByEmail(register.getEmail()) != null) {

			Register userByEmail = getUserByEmail(register.getEmail());
			if (userByEmail.getPassword().equals(register.getPassword())) {

				return userByEmail;
			}
		}

		return null;
	}

	public Register getUserByEmail(String email) {
		return registerRepo.findByEmail(email);
	}

	@Override
	public boolean updateUser(Register register) {
		if (getUserByEmail(register.getEmail()) == null) {
			registerRepo.insert(register);
			return true;

		}else {
			registerRepo.deleteById(register.getId());
			registerRepo.insert(register);
			return true;
			
		}

		
	}


}

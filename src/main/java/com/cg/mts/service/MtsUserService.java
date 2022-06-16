package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.MtsUser;
import com.cg.mts.exception.MtsUserAlreadyExistsException;
import com.cg.mts.exception.MtsUserNotFoundException;
import com.cg.mts.repository.MtsUserRepository;

@Service
public class MtsUserService implements IMtsUserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MtsUserRepository mtsUserRepository;

	MtsUser loggedInUser;

	@Override
	public List<MtsUser> getAllUsers() {
		List<MtsUser> userList = mtsUserRepository.findAll();
		if (userList.isEmpty()) {
			String exceptionMessage = "MtsUsers don't exist in the database.";
			LOG.warn(exceptionMessage);
			throw new MtsUserNotFoundException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return userList;
		}
	}

	@Override
	public MtsUser registerUser(MtsUser mtsUser) {
		LOG.info(mtsUser.toString());
		Optional<MtsUser> userOptional = mtsUserRepository.findById(mtsUser.getUserName());
		if (userOptional.isEmpty()) {
			return mtsUserRepository.save(mtsUser);
		} else {
			String exceptionMessage = "User with userName " + mtsUser.getUserName() + " already exists.";
			throw new MtsUserAlreadyExistsException(exceptionMessage);
		}
	}

	@Override
	public MtsUser loginUser(MtsUser mtsUser) {
		LOG.info(mtsUser.toString());
		Optional<MtsUser> userOptional = mtsUserRepository.findById(mtsUser.getUserName());
		if (userOptional.isPresent()) {
			if (mtsUser.equals(userOptional.get())) {
				LOG.info(userOptional.get().toString());
				loggedInUser = mtsUser; // successful login
				return mtsUser;
			} else {
				String exceptionMessage = "Wrong password!";
				LOG.warn(exceptionMessage);
				throw new MtsUserNotFoundException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "Wrong userName!";
			LOG.warn(exceptionMessage);
			throw new MtsUserNotFoundException(exceptionMessage);
		}
	}

	@Override
	public String logoutUser(String userName) {
		if (loggedInUser.getUserName().equals(userName)) {

			loggedInUser = null;
			return userName;
		} else {
			String exceptionMessage = "User with userName " + userName + " is not logged in.";
			LOG.warn(exceptionMessage);
			throw new MtsUserNotFoundException(exceptionMessage);
		}
	}

	@Override
	public MtsUser updateUser(MtsUser mtsUser) {
		Optional<MtsUser> userOptional = mtsUserRepository.findById(mtsUser.getUserName());
		if (userOptional.isPresent()) {
			LOG.info(userOptional.get().toString());
			return mtsUserRepository.save(mtsUser);
		} else {
			String exceptionMessage = "User with userName " + mtsUser.getUserName() + " not found!";
			LOG.warn(exceptionMessage);
			throw new MtsUserNotFoundException(exceptionMessage);
		}
	}
}
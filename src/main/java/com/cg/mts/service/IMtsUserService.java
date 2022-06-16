package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.MtsUser;

public interface IMtsUserService {

	public abstract List<MtsUser> getAllUsers();

	public abstract MtsUser registerUser(MtsUser appUser);

	public abstract MtsUser loginUser(MtsUser appUser);

	public abstract String logoutUser(String userName);

	public abstract MtsUser updateUser(MtsUser appUser);

}
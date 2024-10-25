package com.roze.admin.setting.state;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.roze.common.entity.Country;
import com.roze.common.entity.State;

public interface StateRepository extends CrudRepository<State, Integer> {
	
	public List<State> findByCountryOrderByNameAsc(Country country);
}

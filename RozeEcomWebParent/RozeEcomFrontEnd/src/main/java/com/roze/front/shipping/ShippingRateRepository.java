package com.roze.front.shipping;

import org.springframework.data.repository.CrudRepository;

import com.roze.common.entity.Country;
import com.roze.common.entity.ShippingRate;

public interface ShippingRateRepository extends CrudRepository<ShippingRate, Integer> {
	
	public ShippingRate findByCountryAndState(Country country, String state);
}

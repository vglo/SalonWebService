package com.habbib.billing.serviceIntefaces;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.habbib.billing.model.Salonservice;

@Service
public interface IBilling {

	public double calculateBill(Map<Optional<Salonservice>, Integer> serviceMap);
}

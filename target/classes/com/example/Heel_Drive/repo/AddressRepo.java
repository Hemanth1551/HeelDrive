package com.example.Heel_Drive.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Heel_Drive.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{
}

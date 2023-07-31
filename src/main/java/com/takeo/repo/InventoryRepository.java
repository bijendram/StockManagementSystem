package com.takeo.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.takeo.Entity.Inventory;


public interface InventoryRepository extends MongoRepository<Inventory, String> {
	
	//List<Inventory> getInventoryByUser(User user);
	
	//List<Inventory> getInventoryByStock(Stock stock);

}

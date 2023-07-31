package com.takeo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeo.Entity.Inventory;
import com.takeo.repo.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepo;
	

	@Override
	public Inventory createInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		Inventory invent= inventoryRepo.save(inventory);
		
		if(invent != null)
			return invent;
		else
			return null;
	}

	@Override
	public Optional<Inventory> getInventoryById(String _id) {
		// TODO Auto-generated method stub
		
		Optional inventory=inventoryRepo.findById(_id);
		
		if(inventory != null)
			return inventory;
		else
			return null;
	}

	
	@Override
	public List<Inventory> getAllInventory() {
		
		List<Inventory> viewInventoryInfo=inventoryRepo.findAll();
		
		
		return viewInventoryInfo;
		
	
	}
	
	@Override
	public boolean cancelInventory(String id) {
		// TODO Auto-generated method stub
		
		Inventory inventory=inventoryRepo.findById(id).get();
		System.out.println(inventory.toString());
		
		if(inventory != null) {
			inventoryRepo.delete(inventory);
			return true;
		}
		return false;
	}

@Override
public Inventory saveOrupdateInventory(Inventory inventory) {
	// TODO Auto-generated method stub
	
	inventoryRepo.save(inventory);
	return inventory;

}

@Override
public int saveInventory(Inventory inventory) {
	// TODO Auto-generated method stub
	
	Inventory saveInvent=inventoryRepo.save(inventory);
	
	if(saveInvent != null)
		return 1;
	else
	return 0;
}




}

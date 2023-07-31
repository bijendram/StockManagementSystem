package com.takeo.service;

import java.util.List;
import java.util.Optional;

import com.takeo.Entity.Inventory;

public interface InventoryService {
	
	
	  	public Inventory createInventory(Inventory inventory);
	    public Optional<Inventory> getInventoryById(String id);
	   // public List<Inventory> getInventoryByUser(User user);
	   // public List<Inventory> getInventoryByStock(Stock stock);
	    public List<Inventory> getAllInventory();
	    public boolean cancelInventory(String id);
	    public Inventory saveOrupdateInventory(Inventory inventory);
	    public int saveInventory(Inventory inventory);

}

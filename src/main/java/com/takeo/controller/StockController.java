package com.takeo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.takeo.Entity.Inventory;
import com.takeo.Entity.Register;
import com.takeo.service.InventoryServiceImpl;
import com.takeo.service.RegisterServiceImpl;

@Controller
public class StockController {

	@Autowired
	private RegisterServiceImpl registerService;

	@Autowired
	private InventoryServiceImpl inventoryService;

	@RequestMapping(value = "/viewAllStocks", method = RequestMethod.GET)
	public String viewAllStocks(@RequestParam("email") String email, Model model) {
		Register user = registerService.getUserByEmail(email);

		List<Inventory> inventoryItems = inventoryService.getAllInventory();

		model.addAttribute("user", user);
		model.addAttribute("inventoryItems", inventoryItems);

		return "viewAllStocks";
	}

	@GetMapping("/buyStock")
	public String showBuyStockForm(Model model, @RequestParam("email") String email) {
		Register user = registerService.getUserByEmail(email);
		List<Inventory> inventoryItems = inventoryService.getAllInventory();
		model.addAttribute("inventoryItems", inventoryItems);
		model.addAttribute("user", user);
		
		return "buyStock";
	}

	@PostMapping("/buyStock")
	public String buyStock(@RequestParam("email") String email,
            @RequestParam("stockId") String stockId,
            @RequestParam("stockName") String stockName,
            @RequestParam("qty") int quantity){
		// Retrieve the selected stock ID and quantity from the inventory object
		
		Optional<Inventory> inventoryById = inventoryService.getInventoryById(stockId);
		Inventory invent = inventoryById.get();
		System.out.println("The stock price is: " +invent.getPrice() );
	
		
		
		Register user = registerService.getUserByEmail(email);
		List<Inventory> stocks = user.getStocks();
		
		
		
		Inventory newInventory = new Inventory();
		newInventory.setQty(Integer.toString(quantity));
		newInventory.setStock(stockName);
		newInventory.setPrice(invent.getPrice());
		
		
		
		stocks.add(newInventory);
		registerService.updateUser(user);
		
		return "redirect:/viewYourStocks?email=" + email;
	}

	@GetMapping("/sellStock")
	public String showBuyStockFormSell(Model model, @RequestParam("email") String email) {
		Register user = registerService.getUserByEmail(email);
		List<Inventory> stocksList = user.getStocks();
		model.addAttribute("user", user);
		model.addAttribute("stocksList", stocksList);
		return "sellStock";
	}

	@PostMapping("/sellStock")
	public String sellStock(@RequestParam("email") String email,
            @RequestParam("stockId") String stockId,
            @RequestParam("stockName") String stockName,
            @RequestParam("qty") int quantity){
		System.out.print("The id is: "+ stockId);
		

		Inventory newInventory = new Inventory();
		newInventory.setQty(Integer.toString(quantity));
		newInventory.setStock(stockName);
		
		Register user = registerService.getUserByEmail(email);
		List<Inventory> stocks = user.getStocks();
		stocks.remove(newInventory);
		
		registerService.updateUser(user);
		
		return "redirect:/viewYourStocks?email=" + email;
	}


	@GetMapping("/viewYourStocks")
	public String viewYourStocks(Model model, @RequestParam("email") String email, @ModelAttribute("inventory") Inventory inventory) {
		Register user = registerService.getUserByEmail(email);
		List<Inventory> allStocks = user.getStocks();
		model.addAttribute("user", user);
		model.addAttribute("allStocks", allStocks);
		
		return "viewYourStocks";
	}
	

	
	
}

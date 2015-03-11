package mx.com.mindbits.argos.inventory.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.mindbits.argos.common.Message;
import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;
import mx.com.mindbits.argos.inventory.webapp.form.ItemCaptureForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CatalogsController {

	@Autowired
	private CatalogManager inventoryManager;
	
	private static final String ITEMS_CATALOG_VIEW = "itemsCatalog";
	
	private static final String ITEM_CAPTURE_VIEW = "itemCapture";
	
	private static final String CATEGORIES_CATALOG_VIEW = "categoriesCatalog";
	
	private static final String UNITS_CATALOG_VIEW = "unitsCatalog";
	
	/// TODO: Consider keeping catalogs in cache 

	@RequestMapping(value = "/listItems", method = RequestMethod.GET)
	public String listItems(Model model, HttpServletRequest request) {
		List<ItemVO> items = inventoryManager.getAllItems();
		model.addAttribute("itemsList", items);
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		return ITEMS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/getItemsBycategory", method = RequestMethod.GET)
	public String getItemsBycategory(Model model, @RequestParam("categoryId") Integer categoryId) {
		List<ItemVO> items = inventoryManager.getItemsByCategory(categoryId);
		model.addAttribute("itemsList", items);
		
		List<CategoryVO> categories = inventoryManager.getAllCategories();
		model.addAttribute("categoriesList", categories);
		model.addAttribute("selectedCategory", categoryId);
		
		return ITEMS_CATALOG_VIEW;
	}

	@RequestMapping(value = "/showItem", method = RequestMethod.GET)
	public String showItem(Model model, @RequestParam("id") Integer itemId) {
		ItemVO item = inventoryManager.getItem(itemId);
		
		if(item != null) {
			List<ItemVO> results = new ArrayList<ItemVO>(1);
			results.add(item);
			
			model.addAttribute("itemsList", results);
		}
		
		return ITEMS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/captureItem", method = RequestMethod.GET)
	public String captureItem(Model model, HttpServletRequest request) {
		List<CategoryVO> categories = inventoryManager.getAllCategories();
		List<UnitOfMeasureVO> unitsOfMeasure = inventoryManager.getAllUnitsOfMeasure();
		
		model.addAttribute("itemCaptureForm", new ItemCaptureForm());
		model.addAttribute("categoriesList", categories);
		model.addAttribute("unitsList", unitsOfMeasure);
		
		return ITEM_CAPTURE_VIEW;
	}
	
	@RequestMapping(value = "/insertItem", method = RequestMethod.POST)
	@ResponseBody
	public Message insertItem(@ModelAttribute(value="itemCaptureForm") ItemCaptureForm itemCaptureForm) {
		Message response;
		
		ItemVO item = itemCaptureForm.getItem();
		ItemLocationVO itemLocation = itemCaptureForm.getLocation();
		
		ItemClassificationVO itemClassification = new ItemClassificationVO();
		itemClassification.setCategory(itemCaptureForm.getCategory());
		
		try {
			item = inventoryManager.createItem(item, itemClassification, itemLocation);
			
			if(item != null && item.getId() != null) {	
				response = Message.successMessage("Nuevo artículo creado", item);
			}else {
				response = Message.failMessage("No fue posible crear el nuevo artículo, intente más tarde");
			}
		}catch(Exception e) {
			response = Message.failMessage("No fue posible crear el nuevo artículo, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	@ResponseBody
	public String updateItem(Model model) {
		List<ItemVO> results = inventoryManager.getAllItems();		
		model.addAttribute("itemsList", results);
		
		return ITEMS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/adminCategories", method = RequestMethod.GET)
	public String listCategories(Model model, HttpServletRequest request) {
//		if (isRememberMeAuthenticated()) {
//			// require password for adminstration
//			setRememberMeTargetUrlToSession(request, "/adminCategories");
//			model.addAttribute("loginUpdate", true);
//			
//			return "appLogin";
//		}
		
		List<CategoryVO> results = inventoryManager.getAllCategories();
		model.addAttribute("categoriesList", results);
		model.addAttribute("category", new CategoryVO());
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		return CATEGORIES_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
	@ResponseBody
	public Message insertCategory(@ModelAttribute(value="category") CategoryVO newCategory) {
		CategoryVO category = newCategory;
		Message response; 
		
		category.setId(null);
		if(category.getParentCategory().getId() == 0) {
			category.setParentCategory(null);
		}
		
		category = inventoryManager.saveCategory(category);
		
		if(category != null && category.getId() != null) {
			response = Message.successMessage("Categoría creada", category);
		}else {
			response = Message.failMessage("No fue posible crear la nueva categoría, intente más tarde");
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	@ResponseBody
	public Message updateCategory(@ModelAttribute(value="category") CategoryVO category) {
		CategoryVO categoryToUpdate = category;
		Message response;
		
		if(categoryToUpdate.getParentCategory().getId() == 0) {
			categoryToUpdate.setParentCategory(null);
		}
		
		categoryToUpdate = inventoryManager.updateCategory(categoryToUpdate);
		if(categoryToUpdate != null) {
			response = Message.successMessage("Categoría actualizada", category);
		}else {
			response = Message.failMessage("No fue posible actualizar la categoría, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	@ResponseBody
	public Message deleteCategory(@ModelAttribute(value="categoryId") Integer categoryId) {
		Message response = Message.successMessage("Categoría eliminada", null);
		
		try {
			inventoryManager.deleteCategory(categoryId);
		}catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				response = Message.failMessage("Imposible realizar la operación, "
						+ "algunos elementos ya se encuentran asignados a esta categoría.");
			}else {
				response = Message.failMessage("No fue posible realizar la operación, intente más tarde.");
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/getCategoryHierarchy", method = RequestMethod.POST)
	@ResponseBody
	public List<CategoryVO> getCategoryHierarchy(@ModelAttribute(value="categoryId") Integer categoryId) {
		return inventoryManager.getCategoryHierachy(categoryId);
	}
	
	@RequestMapping(value = "/getCategoryTree", method = RequestMethod.POST)
	@ResponseBody
	public List<CategoryVO> getCategoryTree() {
		return inventoryManager.getCategoriesTree(null);
	}
	
	@RequestMapping(value = "/adminUnitsOfMeasure", method = RequestMethod.GET)
	public String listUnitsOfMeasure(Model model, HttpServletRequest request) {
		List<UnitOfMeasureVO> results = inventoryManager.getAllUnitsOfMeasure();
		model.addAttribute("unitsList", results);
		model.addAttribute("unitOfMeasure", new UnitOfMeasureVO());
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		return UNITS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/insertUnitOfMeasure", method = RequestMethod.POST)
	@ResponseBody
	public Message insertUnitOfMeasure(@ModelAttribute(value="unitOfMeasure") UnitOfMeasureVO newUnitOfMeasure) {
		UnitOfMeasureVO unitOfMeasure = newUnitOfMeasure;
		Message response; 
		
		unitOfMeasure.setId(null);
		unitOfMeasure = inventoryManager.saveUnitOfMeasure(unitOfMeasure);
		
		if(unitOfMeasure != null && unitOfMeasure.getId() != null) {
			response = Message.successMessage("Unidad de medida creada", unitOfMeasure);
		}else {
			response = Message.failMessage("No fue posible crear la nueva unidad de medida, intente más tarde");
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/updateUnitOfMeasure", method = RequestMethod.POST)
	@ResponseBody
	public Message updateUnitOfMeasure(@ModelAttribute(value="unitOfMeasure") UnitOfMeasureVO unitOfMeasure) {
		UnitOfMeasureVO unitOfMeasureToUpdate = unitOfMeasure;
		Message response;
		
		unitOfMeasureToUpdate = inventoryManager.updateUnitOfMeasure(unitOfMeasureToUpdate);
		if(unitOfMeasureToUpdate != null) {
			response = Message.successMessage("Unidad de medida actualizada", unitOfMeasure);
		}else {
			response = Message.failMessage("No fue posible actualizar la unidad de medida, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/deleteUnitOfMeasure", method = RequestMethod.POST)
	@ResponseBody
	public Message deleteUnitOfMeasure(@ModelAttribute(value="unitId") Integer unitId) {
		Message response = Message.successMessage("Unidad de medida eliminada", null);
		
		try {
			inventoryManager.deleteCategory(unitId);
		}catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				response = Message.failMessage("Imposible realizar la operación, "
						+ "algunos elementos ya se encuentran asignados a unidad de medida.");
			}else {
				response = Message.failMessage("No fue posible realizar la operación, intente más tarde.");
			}
		}
		return response;
	}

	private boolean isRememberMeAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
 
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}
	
	private void setRememberMeTargetUrlToSession(HttpServletRequest request, String url){
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.setAttribute("targetUrl", url);
		}
	}
	
	private Message getAlertMessage(HttpServletRequest request) {
		Message message = null;
		boolean error = "1".equals((String) request.getParameter("error"));
		boolean success = "1".equals((String) request.getParameter("success"));
		String msg = (String) request.getParameter("msg");
		
		if(error || success) {
			if(msg == null || "".equals(msg)) {
				if(error) {
					msg = "No fue posible completar la operación";
				}else if(success){
					msg = "Operación exitosa";
				}
			}
			if(error) {
				message = Message.failMessage(msg);
			}else if(success){
				message = Message.successMessage(msg, null);
			}
		}
		
		return message;
	}
	
}

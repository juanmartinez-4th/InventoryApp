package mx.com.mindbits.argos.inventory.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.mindbits.argos.common.Message;
import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.LocationVO;
import mx.com.mindbits.argos.inventory.vo.ProjectVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;

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
	
	private static final String LOCATIONS_CATALOG_VIEW = "locationsCatalog";
	
	private static final String PROJECTS_CATALOG_VIEW = "projectsCatalog";
	
	private static final String UNITS_CATALOG_VIEW = "unitsCatalog";
	
	/// TODO: Consider keeping catalogs in cache 

	@RequestMapping(value = "/listItems", method = RequestMethod.GET)
	public String listItems(Model model) {
		List<ItemVO> items = inventoryManager.getAllItems();
		model.addAttribute("itemsList", items);
		
		List<CategoryVO> categories = inventoryManager.getAllCategories();
		model.addAttribute("categoriesList", categories);
		
		return ITEMS_CATALOG_VIEW;
	}

	@RequestMapping(value = "/showItem", method = RequestMethod.GET)
	public String showItem(Model model, @RequestParam("id") int id) {
		ItemVO item = inventoryManager.getItem(id);
		
		if(item != null) {
			List<ItemVO> results = new ArrayList<ItemVO>(1);
			results.add(item);
			
			model.addAttribute("itemsList", results);
		}
		
		return ITEMS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/captureItem", method = RequestMethod.GET)
	public String captureItem(Model model) {
		List<CategoryVO> categories = inventoryManager.getAllCategories();
		List<LocationVO> locations = inventoryManager.getAllLocations();
		List<ProjectVO> projects = inventoryManager.getAllProjects();
		List<UnitOfMeasureVO> unitsOfMeasure = inventoryManager.getAllUnitsOfMeasure();
		
		model.addAttribute("categoriesList", categories);
		model.addAttribute("locationsList", locations);
		model.addAttribute("projectsList", projects);
		model.addAttribute("unitsList", unitsOfMeasure);
		
		return ITEM_CAPTURE_VIEW;
	}
	
	@RequestMapping(value = "/insertItem", method = RequestMethod.POST)
	@ResponseBody
	public String insertItem(Model model) {
		List<ItemVO> results = inventoryManager.getAllItems();	
		model.addAttribute("itemsList", results);
		
		return ITEMS_CATALOG_VIEW;
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
//			setRememberMeTargetUrlToSession(request, "/adminCatalogs");
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
	public String getCategoryHierarchy(@ModelAttribute(value="categoryId") Integer categoryId) {
		CategoryVO category = inventoryManager.getCategory(categoryId);
		List<String> hierarchy;
		String response = "";
		
		if(category != null && category.getParentCategory() != null) {
			hierarchy = new ArrayList<String>(1);
			CategoryVO parent = category.getParentCategory();
			
			while(parent != null) {
				hierarchy.add(parent.getName());
				parent = parent.getParentCategory();
			}
			
			for (int i = (hierarchy.size() - 1); i >= 0; i--) {
				response += hierarchy.get(i) + ",";
			}
			
			response += category.getName();
			
			return response;
		}else {
			return category.getName();
		}
	}
	
	@RequestMapping(value = "/adminLocations", method = RequestMethod.GET)
	public String listLocations(Model model, HttpServletRequest request) {
//		if (isRememberMeAuthenticated()) {
//			// require password for adminstration
//			setRememberMeTargetUrlToSession(request, "/adminCatalogs");
//			model.addAttribute("loginUpdate", true);
//			
//			return "appLogin";
//		}
		
		List<LocationVO> results = inventoryManager.getAllLocations();
		model.addAttribute("locationsList", results);
		model.addAttribute("location", new LocationVO());
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		return LOCATIONS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/insertLocation", method = RequestMethod.POST)
	@ResponseBody
	public Message insertLocation(@ModelAttribute(value="location") LocationVO newLocation) {
		LocationVO location = newLocation;
		Message response; 
		
		location.setId(null);
		location = inventoryManager.saveLocation(location);
		
		if(location != null && location.getId() != null) {
			response = Message.successMessage("Ubicación creada", location);
		}else {
			response = Message.failMessage("No fue posible crear la nueva ubicación, intente más tarde");
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
	@ResponseBody
	public Message updateLocation(@ModelAttribute(value="location") LocationVO location) {
		LocationVO locationToUpdate = location;
		Message response;
		
		locationToUpdate = inventoryManager.updateLocation(locationToUpdate);
		if(locationToUpdate != null) {
			response = Message.successMessage("Ubicación actualizada", location);
		}else {
			response = Message.failMessage("No fue posible actualizar la ubicación, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/deleteLocation", method = RequestMethod.POST)
	@ResponseBody
	public Message deleteLocation(@ModelAttribute(value="locationId") Integer locationId) {
		Message response = Message.successMessage("Ubicación eliminada", null);
		
		try {
			inventoryManager.deleteLocation(locationId);
		}catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				response = Message.failMessage("Imposible realizar la operación, "
						+ "algunos elementos ya se encuentran asignados a esta ubicación.");
			}else {
				response = Message.failMessage("No fue posible realizar la operación, intente más tarde.");
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/adminProjects", method = RequestMethod.GET)
	public String listProjects(Model model, HttpServletRequest request) {
		List<ProjectVO> results = inventoryManager.getAllProjects();
		model.addAttribute("projectsList", results);
		model.addAttribute("project", new ProjectVO());
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		return PROJECTS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/insertProject", method = RequestMethod.POST)
	@ResponseBody
	public Message insertProject(@ModelAttribute(value="project") ProjectVO newProject) {
		ProjectVO project = newProject;
		Message response; 
		
		project.setId(null);
		project = inventoryManager.saveProject(project);
		
		if(project != null && project.getId() != null) {
			response = Message.successMessage("Proyecto creado", project);
		}else {
			response = Message.failMessage("No fue posible crear el nuevo proyecto, intente más tarde");
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST)
	@ResponseBody
	public Message updateProject(@ModelAttribute(value="project") ProjectVO project) {
		ProjectVO projectToUpdate = project;
		Message response;
		
		projectToUpdate = inventoryManager.updateProject(projectToUpdate);
		if(projectToUpdate != null) {
			response = Message.successMessage("Proyecto actualizado", project);
		}else {
			response = Message.failMessage("No fue posible actualizar el proyecto, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
	@ResponseBody
	public Message deleteProject(@ModelAttribute(value="projectId") Integer projectId) {
		Message response = Message.successMessage("Proyecto eliminado", null);
		
		try {
			inventoryManager.deleteProject(projectId);
		}catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				response = Message.failMessage("Imposible realizar la operación, "
						+ "algunos elementos ya se encuentran asignados a este proyecto.");
			}else {
				response = Message.failMessage("No fue posible realizar la operación, intente más tarde.");
			}
		}
		return response;
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

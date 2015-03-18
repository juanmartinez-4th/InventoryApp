package mx.com.mindbits.argos.inventory.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.mindbits.argos.common.Message;
import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemPictureVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.ProductionVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;
import mx.com.mindbits.argos.inventory.webapp.form.ItemCaptureForm;
import mx.com.mindbits.argos.inventory.webapp.form.ResultsFilter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatalogsController {

	@Autowired
	private CatalogManager inventoryManager;
	
	private static final String ITEMS_CATALOG_VIEW = "itemsCatalog";
	private static final String ITEMS_CATALOG_VIEW_TITLE = "Artículos";
	
	private static final String ITEM_CAPTURE_VIEW = "itemCapture";
	private static final String ITEM_CAPTURE_VIEW_TITLE = "Captura de artículo";
	
	private static final String ITEM_DETAIL_VIEW = "itemDetail";
	private static final String ITEM_DETAIL_VIEW_TITLE = "Detalle de artículo";
	
	private static final String CATEGORIES_CATALOG_VIEW = "categoriesCatalog";
	private static final String CATEGORIES_CATALOG_VIEW_TITLE = "Categorías";
	
	private static final String PRODUCTIONS_CATALOG_VIEW = "productionsCatalog";
	private static final String PRODUCTIONS_CATALOG_VIEW_TITLE = "Producciones";
	
	private static final String UNITS_CATALOG_VIEW = "unitsCatalog";
	private static final String UNITS_CATALOG_VIEW_TITLE = "Unidades de medida";
	
	/// TODO: Consider keeping catalogs in cache
	
	@Value("${app.inventory.images}")
	private String itemPicturesFolder;

	@RequestMapping(value = "/listItems", method = RequestMethod.GET)
	public String listItems(Model model, 
			@ModelAttribute(value="resultsFilter") ResultsFilter resultsFilter, 
			HttpServletRequest request) {
		List<ItemVO> items;
		
		if(resultsFilter != null && resultsFilter.getFilter1() != null 
				&& !"".equals(resultsFilter.getFilter1().trim())) {
			String itemDescription = resultsFilter.getFilter1().trim().toLowerCase();
			items = inventoryManager.getItemsByDescription(itemDescription);
		}else {
			items = inventoryManager.getAllItems();
		}
		
		ResultsFilter filter = new ResultsFilter();
		filter.setFilterName("listItems");;
		model.addAttribute("resultsFilter", filter);
		model.addAttribute("itemsList", items);
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		model.addAttribute("pageTitle", ITEMS_CATALOG_VIEW_TITLE);
		
		return ITEMS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/getItemsBycategory", method = RequestMethod.GET)
	public String getItemsBycategory(Model model, @RequestParam("categoryId") Integer categoryId) {
		List<ItemVO> items = inventoryManager.getItemsByCategory(categoryId);
		model.addAttribute("itemsList", items);
		
		List<CategoryVO> categories = inventoryManager.getAllCategories();
		model.addAttribute("categoriesList", categories);
		model.addAttribute("selectedCategory", categoryId);
		model.addAttribute("pageTitle", ITEMS_CATALOG_VIEW_TITLE);
		
		return ITEMS_CATALOG_VIEW;
	}

	@RequestMapping(value = "/showItem", method = RequestMethod.GET)
	public String showItem(Model model, @RequestParam("itemId") Integer itemId) {
		ItemCaptureForm itemForm = new ItemCaptureForm();
		List<ItemLocationVO> itemLocation = inventoryManager.getItemLocations(itemId);
		ItemClassificationVO itemClassification = inventoryManager.getItemClassification(itemId);
		List<ItemPictureVO> itemPictures = inventoryManager.getItemPictures(itemId);
		List<String> itemPictureLocations = new ArrayList<String>(itemPictures.size());
		
		itemForm.setCategory(itemClassification.getCategory());
		itemForm.setItem(itemLocation.get(0).getItem());
		itemForm.setLocation(itemLocation.get(0));
		
		for (ItemPictureVO itemPictureVO : itemPictures) {
			String pictLocation = itemForm.getItem().getCode() + "/";
			pictLocation += itemPictureVO.getFileName();
			itemPictureLocations.add(pictLocation);
		}
		itemForm.setItemPictures(itemPictureLocations);
		
		model.addAttribute("selectedItem", itemForm);
		model.addAttribute("pageTitle", ITEM_DETAIL_VIEW_TITLE);
		
		return ITEM_DETAIL_VIEW;
	}
	
	@RequestMapping(value = "/captureItem", method = RequestMethod.GET)
	public String captureItem(Model model, HttpServletRequest request) {
		List<CategoryVO> categories = inventoryManager.getAllCategories();
		List<UnitOfMeasureVO> unitsOfMeasure = inventoryManager.getAllUnitsOfMeasure();
		List<ProductionVO> productions = inventoryManager.getAllProductions();
		BigInteger nextId = inventoryManager.getNextItemId();
		
		model.addAttribute("itemCaptureForm", new ItemCaptureForm());
		model.addAttribute("categoriesList", categories);
		model.addAttribute("unitsList", unitsOfMeasure);
		model.addAttribute("productionsList", productions);
		model.addAttribute("nextItemId", StringUtils.leftPad(nextId != null ? nextId.toString() : "", 9, "0"));
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		model.addAttribute("pageTitle", ITEM_CAPTURE_VIEW_TITLE);
		
		return ITEM_CAPTURE_VIEW;
	}
	
	@RequestMapping(value = "/insertItem", method = RequestMethod.POST)
	public String insertItem(@ModelAttribute(value="itemCaptureForm") ItemCaptureForm itemCaptureForm,
			Model model, RedirectAttributes redirectAttrs) {
		Message response;
		boolean redirectNew = itemCaptureForm.getRedirectNew();
		
		ItemVO item = itemCaptureForm.getItem();
		ItemLocationVO itemLocation = itemCaptureForm.getLocation();
		
		ItemClassificationVO itemClassification = new ItemClassificationVO();
		itemClassification.setCategory(itemCaptureForm.getCategory());
		List<ItemPictureVO> itemPictures;
		
		try {
			List<String> pictureFiles = savePictures(item.getCode(), itemCaptureForm.getPictureFiles());
			item.setDefaultPicture(pictureFiles.get(0));
			itemPictures = new ArrayList<ItemPictureVO>(pictureFiles.size());
			for (String fileName : pictureFiles) {
				ItemPictureVO itemPicture = new ItemPictureVO();
				itemPicture.setFileName(fileName);
				
				itemPictures.add(itemPicture);
			}
			item = inventoryManager.createItem(item, itemClassification, itemLocation, itemPictures);
			
			if(item != null && item.getId() != null) {	
				response = Message.successMessage("Nuevo artículo creado", item);
			}else {
				response = Message.failMessage("No fue posible crear el nuevo artículo, intente más tarde");
			}
		}catch(Exception e) {
			response = Message.failMessage("No fue posible crear el nuevo artículo, intente más tarde");
		}
		
		redirectAttrs.addFlashAttribute("alertMsg", response);
		
		if(redirectNew) {
			return "redirect:/captureItem";
		}else {
			return "redirect:/listItems";
		}
		
	}
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	@ResponseBody
	public String updateItem(Model model) {
		List<ItemVO> results = inventoryManager.getAllItems();		
		model.addAttribute("itemsList", results);
		model.addAttribute("pageTitle", ITEMS_CATALOG_VIEW_TITLE);
		
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
		
		model.addAttribute("pageTitle", CATEGORIES_CATALOG_VIEW_TITLE);
		
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
		
		model.addAttribute("pageTitle", UNITS_CATALOG_VIEW_TITLE);
		
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
						+ "esta unidad de medida ya se encuentra asignada a algunos elementos.");
			}else {
				response = Message.failMessage("No fue posible realizar la operación, intente más tarde.");
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/adminProductions", method = RequestMethod.GET)
	public String listProductions(Model model, HttpServletRequest request) {
		List<ProductionVO> results = inventoryManager.getAllProductions();
		model.addAttribute("productionsList", results);
		model.addAttribute("production", new ProductionVO());
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		model.addAttribute("pageTitle", PRODUCTIONS_CATALOG_VIEW_TITLE);
		
		return PRODUCTIONS_CATALOG_VIEW;
	}
	
	@RequestMapping(value = "/insertProduction", method = RequestMethod.POST)
	@ResponseBody
	public Message insertProduction(@ModelAttribute(value="production") ProductionVO newProduction) {
		ProductionVO production = newProduction;
		Message response; 
		
		production.setId(null);
		production = inventoryManager.saveProduction(production);
		
		if(production != null && production.getId() != null) {
			response = Message.successMessage("Nueva producción creada", production);
		}else {
			response = Message.failMessage("No fue posible crear la nueva producción, intente más tarde");
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/updateProduction", method = RequestMethod.POST)
	@ResponseBody
	public Message updateProduction(@ModelAttribute(value="production") ProductionVO production) {
		ProductionVO productionToUpdate = production;
		Message response;
		
		productionToUpdate = inventoryManager.updateProduction(productionToUpdate);
		if(productionToUpdate != null) {
			response = Message.successMessage("Producción actualizada", productionToUpdate);
		}else {
			response = Message.failMessage("No fue posible actualizar la producción, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/deleteProduction", method = RequestMethod.POST)
	@ResponseBody
	public Message deleteProduction(@ModelAttribute(value="productionId") Integer productionId) {
		Message response = Message.successMessage("Producción eliminada", null);
		
		try {
			inventoryManager.deleteProduction(productionId);
		}catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				response = Message.failMessage("Imposible realizar la operación, "
						+ "algunos elementos ya se encuentran asignados a esta producción.");
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
	
	private List<String> savePictures(String itemCode, List<MultipartFile> pictureFiles) 
			throws IllegalStateException, IOException {
        List<String> fileNames = new ArrayList<String>(1);
        String savePath = itemPicturesFolder;
 
        int pictId = 0;
        
        if (null != pictureFiles && pictureFiles.size() > 0) {
        	File file = new File(savePath + itemCode);
    		
    		if(!file.exists()) {
    			file.mkdir();
    			savePath = savePath + itemCode + "/";
    		}
        	
            for (MultipartFile multipartFile : pictureFiles) {
            	if(multipartFile.getSize() > 0) {            		
            		String extension = multipartFile.getOriginalFilename();
            		extension = extension.substring(extension.lastIndexOf("."));
	                String fileName = itemCode + "_p" + pictId + extension;
	                // Handle file content - multipartFile.getInputStream()
	                multipartFile.transferTo(new File(savePath + fileName));
	                fileNames.add(fileName);
            	}
            	pictId++;
            }
        }
        
        return fileNames;
	}
	
}

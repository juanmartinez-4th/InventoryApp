package mx.com.mindbits.argos.inventory.webapp.controller;

import static mx.com.mindbits.argos.common.RequestUtils.getAlertMessage;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.com.mindbits.argos.common.Message;
import mx.com.mindbits.argos.inventory.bsn.BarcodeGenerator;
import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.bsn.impl.ImageBarcodeGenerator;
import mx.com.mindbits.argos.inventory.bsn.impl.PdfBarcodeGenerator;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.pdf.codec.Base64;

@Controller
public class CatalogsController {
	
	private static final Logger LOGGER = Logger.getLogger(CatalogsController.class);

	@Autowired
	private CatalogManager inventoryManager;
	
	private static final String ITEMS_CATALOG_LIST_VIEW = "itemsCatalogList";
	private static final String ITEMS_CATALOG_GRID_VIEW = "itemsCatalogGrid";
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
	
	@Value("${app.inventory.tags.columns}")
	private int tagColumns;

	@RequestMapping(value = "/listItems", method = RequestMethod.GET)
	public String listItems(Model model, 
			@ModelAttribute(value="resultsFilter") ResultsFilter resultsFilter, 
			HttpServletRequest request) {
		List<ItemVO> items;
		boolean showGridView = false;
		
		if(resultsFilter != null && resultsFilter.getFilter1() != null 
				&& !"".equals(resultsFilter.getFilter1().trim())) {
			String itemDescription = resultsFilter.getFilter1().trim().toLowerCase();
			items = inventoryManager.getItemsByDescription(itemDescription);
		}else {
			items = inventoryManager.getAllItems();
		}
		
		ResultsFilter filter = new ResultsFilter();
		filter.setFilterName("listItems");
		model.addAttribute("resultsFilter", filter);
		model.addAttribute("itemsList", items);
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		if(request.getParameter("showGrid") != null) {
			showGridView = true;
		}
		
		model.addAttribute("pageTitle", ITEMS_CATALOG_VIEW_TITLE);
		
		return showGridView ? ITEMS_CATALOG_GRID_VIEW : ITEMS_CATALOG_LIST_VIEW;
	}
	
	@RequestMapping(value = "/getItemsBycategory", method = RequestMethod.GET)
	public String getItemsBycategory(Model model, 
			@RequestParam("categoryId") Integer categoryId,
			@ModelAttribute(value="resultsFilter") ResultsFilter resultsFilter,
			HttpServletRequest request) {
		boolean showGridView = false;
		List<ItemVO> items;
		
		if(resultsFilter != null && resultsFilter.getFilter1() != null 
				&& !"".equals(resultsFilter.getFilter1().trim())) {
			String itemDescription = resultsFilter.getFilter1().trim().toLowerCase();
			items = inventoryManager.getItemsByDescription(itemDescription);
		}else {
			items = inventoryManager.getItemsByCategory(categoryId);
		}
		
		ResultsFilter filter = new ResultsFilter();
		filter.setFilterName("listItems");
		model.addAttribute("resultsFilter", filter);
		model.addAttribute("itemsList", items);
		
		List<CategoryVO> categories = inventoryManager.getAllCategories();
		model.addAttribute("categoriesList", categories);
		model.addAttribute("selectedCategory", categoryId);
		
		if(request.getParameter("showGrid") != null) {
			showGridView = true;
		}
		
		model.addAttribute("pageTitle", ITEMS_CATALOG_VIEW_TITLE);
		
		return showGridView ? ITEMS_CATALOG_GRID_VIEW : ITEMS_CATALOG_LIST_VIEW;
	}

	@RequestMapping(value = "/showItem", method = RequestMethod.GET)
	public String showItem(Model model, @RequestParam("itemId") Integer itemId, RedirectAttributes redirectAttrs) {
		ItemCaptureForm itemForm = new ItemCaptureForm();
		List<ItemLocationVO> itemLocation;
		ItemClassificationVO itemClassification;
		List<ItemPictureVO> itemPictures;
		
		try {
			itemLocation = inventoryManager.getItemLocations(itemId);
			itemClassification = inventoryManager.getItemClassification(itemId);
			itemPictures = inventoryManager.getItemPictures(itemId);
		}catch(Exception e) {
			LOGGER.error("Error retrieving item detail: " + e.getMessage(), e);
			Message message = Message.failMessage("No fue posible obtener los detalles del artículo");
			redirectAttrs.addFlashAttribute("alertMsg", message);
			
			return "redirect:/listItems";
		}
		
		List<String> itemPictureLocations = new ArrayList<String>(itemPictures.size());
		
		itemForm.setCategory(itemClassification.getCategory());
		itemForm.setItem(itemLocation.get(0).getItem());
		itemForm.setLocation(itemLocation.get(0));
		
		BarcodeGenerator barcodeGenerator = new ImageBarcodeGenerator();
		byte[] pdfByteArray = barcodeGenerator.generateCode128(itemForm.getItem().getCode(), 1);
		
	    String barcodeStr = Base64.encodeBytes(pdfByteArray);
	    itemForm.setItemBarcode(barcodeStr);
		
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
			
			if(itemLocation.getProduction().getId() == 0) {
				itemLocation.setProduction(null);
			}
			
			item = inventoryManager.createItem(item, itemClassification, itemLocation, itemPictures);
			
			if(item != null && item.getId() != null) {	
				response = Message.successMessage("Nuevo artículo creado", item);
			}else {
				response = Message.failMessage("No fue posible crear el nuevo artículo, intente más tarde");
			}
		}catch(Exception e) {
			LOGGER.error("Error saving item: " + e.getMessage(), e);
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
		
		return ITEMS_CATALOG_GRID_VIEW;
	}
	
	@RequestMapping(value = "/adminCategories", method = RequestMethod.GET)
	public String listCategories(Model model,
			@ModelAttribute(value="resultsFilter") ResultsFilter resultsFilter,
			HttpServletRequest request) {
		Integer parentCategory = null;
		List<CategoryVO> results;
		
		if(resultsFilter != null && resultsFilter.getFilter1() != null 
				&& !"".equals(resultsFilter.getFilter1().trim())) {
			String categoryName = resultsFilter.getFilter1().trim().toLowerCase();
			results = inventoryManager.getCategoriesByName(categoryName);
			model.addAttribute("listByName", "true");
		}else {
			if(request.getParameter("parentCategory") != null) {
				parentCategory = Integer.valueOf(request.getParameter("parentCategory"));
				model.addAttribute("parentCategory", parentCategory);
			}
			results = inventoryManager.getCategoryDescendants(parentCategory);
		}
		
		ResultsFilter filter = new ResultsFilter();
		filter.setFilterName("adminCategories");
		model.addAttribute("resultsFilter", filter);
		
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
				LOGGER.error("Error saving category: " + e.getMessage(), e);
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
	public String listUnitsOfMeasure(Model model,
			@ModelAttribute(value="resultsFilter") ResultsFilter resultsFilter,
			HttpServletRequest request) {
		
		List<UnitOfMeasureVO> results;
		if(resultsFilter != null && resultsFilter.getFilter1() != null 
				&& !"".equals(resultsFilter.getFilter1().trim())) {
			String unitName = resultsFilter.getFilter1().trim().toLowerCase();
			results = inventoryManager.getUnitsOfMeasureByName(unitName);
		}else {
			results = inventoryManager.getAllUnitsOfMeasure();
		}
		
		ResultsFilter filter = new ResultsFilter();
		filter.setFilterName("adminUnitsOfMeasure");
		model.addAttribute("resultsFilter", filter);
		
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
			inventoryManager.deleteUnitOfMeasure(unitId);
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
	public String listProductions(Model model,
			@ModelAttribute(value="resultsFilter") ResultsFilter resultsFilter,
			HttpServletRequest request) {
		List<ProductionVO> results;
		
		if(resultsFilter != null && resultsFilter.getFilter1() != null 
				&& !"".equals(resultsFilter.getFilter1().trim())) {
			String productionName = resultsFilter.getFilter1().trim().toLowerCase();
			results = inventoryManager.getProductionsByName(productionName);
		}else {
			results = inventoryManager.getAllProductions();
		}
		
		ResultsFilter filter = new ResultsFilter();
		filter.setFilterName("adminProductions");
		model.addAttribute("resultsFilter", filter);
		
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
				LOGGER.error("Error saving production: " + e.getMessage(), e);
				response = Message.failMessage("No fue posible realizar la operación, intente más tarde.");
			}
		}
		return response;
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
    		}
    		
    		savePath = savePath + itemCode + "/";
        	
            for (MultipartFile multipartFile : pictureFiles) {
            	if(multipartFile != null && multipartFile.getSize() > 0) {            		
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
	
	@RequestMapping(value = "/getItemTag", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getItemTag(@ModelAttribute(value="code") String code,
			@ModelAttribute(value="copies") Integer copies) {
		String filename = "tags_" + code + ".pdf";
		BarcodeGenerator barcodeGenerator = new PdfBarcodeGenerator(tagColumns);
		byte[] pdfByteArray = barcodeGenerator.generateCode128(code, copies);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    
	    String responseStr = Base64.encodeBytes(pdfByteArray);
	    
//	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfByteArray, headers, HttpStatus.OK);
	    ResponseEntity<String> response = new ResponseEntity<String>(responseStr, headers, HttpStatus.OK);
	    
	    return response;
	}
	
}

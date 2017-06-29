package com.myretail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.model.business.PriceInfo;
import com.myretail.model.business.Product;
import com.myretail.model.repository.ProductPrice;
import com.myretail.model.service.ProductName;
import com.myretail.repository.ProductPriceRepository;

/**
 * This class acts as a middle layer between back-end service providers and
 * front-end REST API controllers. It constructs productObject for GET API by
 * calling ProductNameService and ProductPriceRepository service. It also
 * updates price of the product for PUT API.
 * 
 * @author Aruna Sai
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductNameService productNameService;
	@Autowired
	private ProductPriceRepository productPriceRepository;

	/**
	 * This method calls productNameService and productPriceRepository to get
	 * name and price of the product.
	 * 
	 * @param productID
	 *            productID of type Long
	 * @return Product object.
	 * @throws Exception
	 */
	public Product getProductInfo(Long productID) throws Exception {
		ProductName productName = productNameService.getProductName(productID);
		ProductPrice productPrice = productPriceRepository.getProductPrice(productID);
		return constructProduct(productID, productPrice, productName);
	}

	/**
	 * 
	 * @param productID
	 *            productID of type Long
	 * @param price
	 *            price of the product having type Float
	 * @return true for successful update and false for unsuccessful update
	 * @throws Exception
	 */
	public boolean updateProductPriceInfo(Long productID, Float price) throws Exception {
		return productPriceRepository.updateProductPrice(productID, price);
	}

	/**
	 * This method constructs the Product object from the given input
	 * parameters.
	 * 
	 * @param productID
	 *            productID of type Long
	 * @param productPrice
	 *            ProductPrice object representing price information of the
	 *            product.
	 * @param productName
	 *            ProductName object representing name of the product.
	 * @return Product object.
	 */

	public Product constructProduct(Long productID, ProductPrice productPrice, ProductName productName) {

		Product productObject = new Product();
		productObject.setProductName(productName.getProductName());
		productObject.setProductID(productID);

		PriceInfo priceInfo = new PriceInfo();
		// If there exists no record associated with product in productprices
		// repository
		// then price of the product is set to null.
		if (productPrice == null) {
			priceInfo.setCurrency(null);
			priceInfo.setPrice(null);
		} else {
			priceInfo.setCurrency(productPrice.getCurrency());
			priceInfo.setPrice(productPrice.getPrice());
		}
		productObject.setPriceInfo(priceInfo);
		return productObject;
	}

}

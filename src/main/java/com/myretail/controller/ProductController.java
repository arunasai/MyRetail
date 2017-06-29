package com.myretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.business.Product;
import com.myretail.service.ProductService;

/**
 * This class contains RESTFUL services to get product details(i.e., name and
 * price of the product) and also to update the price of the product.
 * 
 * @author Aruna Sai
 * @version 1.0
 */

@RestController
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductService productService;

	/**
	 * This is a HTTP GET method which is used to get product details like name
	 * and price information.
	 * 
	 * @param productID
	 *            of type long is given as input parameter to the method
	 * @return an object containing productID,name and price information which
	 *         is of type Product Class
	 * @throws Exception
	 */
	@RequestMapping(value = "products/{productID:[\\d]+}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("productID") Long productID) throws Exception {
		// This method throws IllegalArgumentException if productID value is
		// negative or is of type null.
		if (productID == null || productID < 0) {
			throw new IllegalArgumentException("productID parameter cannot be null or less than 0");
		}
		Product product = productService.getProductInfo(productID);
		// This method throws ProductNotFoundException when ProductService is
		// unable
		// to retrieve the product details.
		if (product.getProductName() == null || product.getProductID() == null)
			throw new ProductNotFoundException("ProductID you are searching is not found");

		return product;
	}

	/**
	 * This is a HTTP PUT method which is used to update the price and currency
	 * of the product.
	 * 
	 * @param productID
	 *            of the product whose price has to be updated
	 * @param Product
	 *            class object representing the json Product object
	 * @return Success or Failure message of the update operation
	 * @throws Exception
	 */
	@RequestMapping(value = "products/{productID}", method = RequestMethod.PUT)
	public String updateProductPriceInfo(@PathVariable("productID") Long productID, @RequestBody Product product)
			throws Exception {
		// This method throws IllegalArgumentException if productID value is
		// negative or is of type null.

		if (productID == null || productID < 0) {
			throw new IllegalArgumentException("productID parameter cannot be null or less than 0");
		}
		boolean updated = productService.updateProductPriceInfo(productID, product.getPriceInfo().getPrice());
		// This method returns Success or Failure message based on the response
		// from update operation.
		if (updated)
			return "Given product with id " + productID + " is successfully updated";
		else
			return productID + " is not updated";
	}

}

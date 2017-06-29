package com.myretail.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.myretail.constant.APIConstants;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.service.ProductName;

@Service
@PropertySource("classpath:application.properties")

/**
 * This class is responsible for constructing the ProductName object by parsing
 * the JSON object returned by Target end point
 * 
 * @author Aruna Sai
 *
 */
public class ProductNameService {

	@Value("${restservice.product.baseurl}")
	private String baseUrl;

	@Value("${restservice.product.excludeparams}")
	private String excludeParams;

	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * This method handles the call to Target RestService
	 * 
	 * @param productID
	 *            productID of the product given as input
	 * @return ProductName
	 * @throws Exception
	 */
	public ProductName getProductName(Long productID) throws Exception {

		String endPointURL = baseUrl + productID + excludeParams;
		String endPointResponse = null;
		try {
			endPointResponse = restTemplate.getForObject(endPointURL, String.class);
		} catch (HttpClientErrorException e) {
			throw new ProductNotFoundException("No matching product found for the given ProductID");
		}
		if (endPointResponse == null) {
			throw new ProductNotFoundException("No matching product found for the given ProductID");
		}
		JSONObject requestJsonObject = new JSONObject(endPointResponse);
		ProductName productName = parseJson(requestJsonObject);
		return productName;
	}

	/**
	 * 
	 * This method parses the JSON object and constructs ProductName Object
	 * 
	 * @param requestJsonObject
	 *            This is the JSON object returned by Target end point.
	 * @return ProductName
	 * @throws JSONException
	 */
	public ProductName parseJson(JSONObject requestJsonObject) throws JSONException {
		ProductName productName = new ProductName();
		JSONObject product = requestJsonObject.getJSONObject(APIConstants.PRODUCT);
		JSONObject item = product.getJSONObject(APIConstants.ITEM);
		JSONObject productDescription = item.getJSONObject(APIConstants.PRODUCT_DESCRIPTION);
		productName.setProductName(productDescription.getString(APIConstants.TITLE));
		return productName;
	}

}

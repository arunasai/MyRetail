package com.myretail.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.model.service.ProductName;

/**
 * This class tests the ProductNameService.
 * 
 * @author Aruna Sai
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductNameServiceTest {

	@Mock
	private ProductNameService productNameService;
	Long productID = (long) 13860428;
	String name = "The Big Lebowski (Blu-ray)";

	/**
	 * This method tests getProductName() method. A mock ProductNameService is
	 * created and the response from the service is evaluated against a test
	 * product name value.
	 * 
	 * @throws Exception
	 */

	@Test
	public void getProductName() throws Exception {
		ProductName productName = new ProductName();
		productName.setProductName(name);
		Mockito.when(productNameService.getProductName(productID)).thenReturn(productName);
		ProductName result = productNameService.getProductName(productID);
		assertEquals(result.getProductName(), productName.getProductName());
	}
}

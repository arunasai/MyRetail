package com.myretail.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.myretail.model.business.PriceInfo;
import com.myretail.model.business.Product;
import com.myretail.model.repository.ProductPrice;
import com.myretail.model.service.ProductName;
import com.myretail.repository.ProductPriceRepository;
import com.myretail.service.ProductService;

/**
 * This class does unit test on the getProductInfo method in ProductService.
 * 
 * @author Aruna Sai
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductNameService productNameService;
	@Mock
	private ProductPriceRepository productPriceRepository;

	Long productID = (long) 13860428;
	PriceInfo priceInfo = new PriceInfo("USD", (float) 529.9);
	Product mockProduct = new Product(productID, "The Big Lebowski (Blu-ray)", priceInfo);

	/**
	 * This method performs a unit test on getProductInfo method by creating
	 * mock ProductNameService and ProductPriceRepository Service. It constructs
	 * a mock product and the response from both the services for a given
	 * productID is evaluated against the mock product.
	 * 
	 * @throws Exception
	 */

	@Test
	public void getProductInfo() throws Exception {

		ProductPrice productPrice = new ProductPrice();
		productPrice.setCurrency(priceInfo.getCurrency());
		productPrice.setPrice(priceInfo.getPrice());
		productPrice.setProductID(productID);

		ProductName productName = new ProductName();
		productName.setProductName(mockProduct.getProductName());

		Mockito.when(productNameService.getProductName(productID)).thenReturn(productName);
		Mockito.when(productPriceRepository.getProductPrice(productID)).thenReturn(productPrice);

		Product result = productService.getProductInfo(productID);
		assertEquals(result.getPriceInfo().getCurrency(), mockProduct.getPriceInfo().getCurrency());
		assertEquals(result.getPriceInfo().getPrice(), mockProduct.getPriceInfo().getPrice());
		assertEquals(result.getProductID(), mockProduct.getProductID());
		assertEquals(result.getProductName(), mockProduct.getProductName());

	}

}

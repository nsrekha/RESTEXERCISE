package com.javatpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private IProductService productService;
	
//mapping the getProduct() method to /product

//******GET display***********
//curl http://localhost:8080/product

	@GetMapping(value = "/product")
	public List<Product> getProduct() {
//finds all the products
		List<Product> products = productService.findAll();
//returns the product list
		return products;
	}



//******************GET display particular item*

//curl http://localhost:8080/product/101
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable("id") int id) {
		List<Product> products = productService.findAll();
		// Product product =new Product();
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;

	}

//*******************DELETE

//curl -X DELETE http://localhost:8080/product/103

	@DeleteMapping("/product/{id}")
	public List<Product> deleteproduct(@PathVariable("id") int id) {
		List<Product> products = productService.findAll();
		for (Product product : products) {
			if (product.getId() == id) {
				products.remove(product);
				break;
			}
		}
		return products;
	}

//********PUT for UPDATE*******
	/*
	 * curl -H "Content-Type: application/json" --request PUT -d
	 * '{"id":102,"pname":"UPDATED PRODUCT","batchno":"99","price":99,"noofproduct":9}'
	 * http://localhost:8080/product/102
	 */

	@PutMapping("/product/{id}")
	public List<Product> updateproduct(@PathVariable("id") int id, @RequestBody Product p) {
		List<Product> products = productService.findAll();
		for (Product product : products) {
			if (product.getId() == id) {
				product.setPname(p.getPname());
				product.setBatchno(p.getBatchno());
				product.setPrice(p.getPrice());
				product.setNoofproduct(p.getNoofproduct());
				break;
			}
		}
		return products;
	}

//***POST  insert***
	/* curl -H "Content-Type: application/json" --request POST -d
	 * '{"id":111,"pname":"NEW PRODUCT","batchno":"99","price":99,"noofproduct":9}'
	 * http://localhost:8080/product/
	 * 
	 */
	@PostMapping("/product")
	public List<Product> insertproduct(@RequestBody Product p) {
		List<Product> products = productService.findAll();
		products.add(p);

		return products;
	}

}


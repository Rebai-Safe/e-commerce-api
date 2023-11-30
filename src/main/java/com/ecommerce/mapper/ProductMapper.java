package com.ecommerce.mapper;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

  public ProductDto productToProductDto(Product product){
       ProductDto productDto = new ProductDto();
       productDto.setProductId(product.getProductId());
       productDto.setProductName(product.getProductName());
       productDto.setProductDescription(product.getProductDescription());
       productDto.setProductActualPrice(product.getProductActualPrice());
       productDto.setProductDiscountedPrice(product.getProductDiscountedPrice());
       productDto.setProductImages(product.getProductImages());

       return productDto;
   }
}

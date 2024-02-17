/*
 * Copyright 2018-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omernaci.cucumberexample.service.impl;

import com.omernaci.cucumberexample.persistence.entity.Discount;
import com.omernaci.cucumberexample.persistence.entity.Product;
import com.omernaci.cucumberexample.persistence.repository.ProductRepository;
import com.omernaci.cucumberexample.service.ProductService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BigDecimal applyDiscount(Long productId, Long discountId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {

            Product product  = productOptional.get();
            Discount discount = product.getDiscounts()
                .stream()
                .filter(d -> d.getId().equals(discountId))
                .findFirst()
                .orElse(null);

            if (discount != null && isDiscountValid(discount)) {
                return product.getPrice().subtract(
                    product.getPrice().multiply(
                        discount.getDiscountPercentage().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)));
            }

        }

        return BigDecimal.ZERO;
    }

    private boolean isDiscountValid(Discount discount) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(discount.getStartDate()) && currentDate.isBefore(discount.getEndDate());
    }

}

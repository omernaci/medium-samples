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

package com.omernaci.cucumberexample.steps;

import com.omernaci.cucumberexample.persistence.entity.Discount;
import com.omernaci.cucumberexample.persistence.entity.Product;
import com.omernaci.cucumberexample.persistence.repository.ProductRepository;
import com.omernaci.cucumberexample.service.ProductService;
import com.omernaci.cucumberexample.service.impl.ProductServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ApplyDiscountStep {

    @Mock
    ProductRepository productRepository;
    ProductService productService;

    private Product product;
    private Discount discount;
    private BigDecimal discountedPrice;

    public ApplyDiscountStep() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Given("a product with name {string}, ID {long}, price {double}")
    public void aProductWithNameIDPrice(String name, Long id, Double price) {
        product = new Product();
        product.setName(name);
        product.setId(id);
        product.setPrice(BigDecimal.valueOf(price));
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
    }

    @Given("a discount with ID {long}, {int}% discount, starting on {string}, ending on {string}")
    public void aDiscountWithIDDiscountStartingOnEndingOn(Long id, Integer discountPercentage, String startDate, String endDate) {
        discount = new Discount();
        discount.setId(id);
        discount.setDiscountPercentage(BigDecimal.valueOf(discountPercentage));
        discount.setStartDate(LocalDate.parse(startDate));
        discount.setEndDate(LocalDate.parse(endDate));
        product.setDiscounts(List.of(discount));
    }

    @When("the discount is applied to the product")
    public void theDiscountIsAppliedToTheProduct() {
        discountedPrice = productService.applyDiscount(product.getId(), discount.getId());
    }

    @Then("the discounted price should be {double}")
    public void theDiscountedPriceShouldBe(Double expectedDiscountedPrice) {
        BigDecimal expectedPrice = BigDecimal.valueOf(expectedDiscountedPrice).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedPrice, discountedPrice.setScale(2, RoundingMode.HALF_UP));
    }

}

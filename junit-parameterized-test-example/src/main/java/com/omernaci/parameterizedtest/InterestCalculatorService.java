/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omernaci.parameterizedtest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class InterestCalculatorService {

    public BigDecimal calculateInterest(String accountType, BigDecimal balance) {
        BigDecimal rate = switch (accountType.toUpperCase()) {
            case "SAVINGS" -> BigDecimal.valueOf(0.04); // 4% interest
            case "CURRENT" -> BigDecimal.valueOf(0.02); // 2% interest
            case "FIXED" -> BigDecimal.valueOf(0.06); // 6% interest
            default -> throw new IllegalArgumentException("Invalid account type");
        };

        return balance.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }

}

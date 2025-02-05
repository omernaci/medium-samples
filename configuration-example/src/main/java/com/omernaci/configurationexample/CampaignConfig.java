package com.omernaci.configurationexample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "campaign")
public class CampaignConfig {

    private String currency;
    private List<String> allowedCountries;
    private GeneralConfig general;
    private DiscountConfig discount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getAllowedCountries() {
        return allowedCountries;
    }

    public void setAllowedCountries(List<String> allowedCountries) {
        this.allowedCountries = allowedCountries;
    }

    public GeneralConfig getGeneral() {
        return general;
    }

    public void setGeneral(GeneralConfig general) {
        this.general = general;
    }

    public DiscountConfig getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountConfig discount) {
        this.discount = discount;
    }

    public static class GeneralConfig {
        private int maxActiveCampaigns;
        private int defaultDurationDays;

        public int getMaxActiveCampaigns() { return maxActiveCampaigns; }
        public void setMaxActiveCampaigns(int maxActiveCampaigns) { this.maxActiveCampaigns = maxActiveCampaigns; }

        public int getDefaultDurationDays() { return defaultDurationDays; }
        public void setDefaultDurationDays(int defaultDurationDays) { this.defaultDurationDays = defaultDurationDays; }
    }

    public static class DiscountConfig {
        private int maxDiscountPercentage;
        private int minOrderValue;

        public int getMaxDiscountPercentage() { return maxDiscountPercentage; }
        public void setMaxDiscountPercentage(int maxDiscountPercentage) { this.maxDiscountPercentage = maxDiscountPercentage; }

        public int getMinOrderValue() { return minOrderValue; }
        public void setMinOrderValue(int minOrderValue) { this.minOrderValue = minOrderValue; }
    }

}

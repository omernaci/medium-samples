package com.omernaci.configurationexample;

import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    private final CampaignConfig campaignConfig;

    public CampaignService(CampaignConfig campaignConfig) {
        this.campaignConfig = campaignConfig;
    }

    public void printCampaignSettings() {
        System.out.println("Currency: " + campaignConfig.getCurrency());
        System.out.println("Allowed Countries: " + campaignConfig.getAllowedCountries());
        System.out.println("Max Active Campaigns: " + campaignConfig.getGeneral().getMaxActiveCampaigns());
        System.out.println("Default Duration: " + campaignConfig.getGeneral().getDefaultDurationDays() + " days");
        System.out.println("Max Discount %: " + campaignConfig.getDiscount().getMaxDiscountPercentage());
    }

}

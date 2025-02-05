package com.omernaci.configurationexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CampaignRunner implements CommandLineRunner {

    private final CampaignService campaignService;

    public CampaignRunner(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    public void run(String... args) {
        campaignService.printCampaignSettings();
    }

}

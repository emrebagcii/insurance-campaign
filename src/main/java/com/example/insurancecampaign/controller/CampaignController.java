package com.example.insurancecampaign.controller;


import com.example.insurancecampaign.model.Campaign;
import com.example.insurancecampaign.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("/createCampaign")
    public void createCampaign(@RequestBody Campaign campaign){
        campaignService.createCampaign(campaign);
    }

    @GetMapping("/campaign")
    public List<Campaign> getCampaign() {
        return campaignService.getCampaign();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable(value = "id") Long id){
        return campaignService.getCampaignById(id);
    }

    @PutMapping("/active/{id}")
    public void activateCampaign(@PathVariable(value="id") Long id){
        campaignService.activateCampaign(id);
    }

    @PutMapping("/deactive/{id}")
    public void deactivateCampaing(@PathVariable(value="id")Long id){
        campaignService.deactivateCampaign(id);
    }


}

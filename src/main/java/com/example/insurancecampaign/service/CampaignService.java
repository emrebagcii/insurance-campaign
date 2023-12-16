package com.example.insurancecampaign.service;

import com.example.insurancecampaign.enums.CampaignCategory;
import com.example.insurancecampaign.enums.CampaignStatus;
import com.example.insurancecampaign.model.Campaign;
import com.example.insurancecampaign.model.CampaignHistory;
import com.example.insurancecampaign.repository.CampaignHistoryRepository;
import com.example.insurancecampaign.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final CampaignHistoryRepository campaignHistoryRepository;


    public void createCampaign(Campaign campaign) {
        campaign.setStatus(setStatus(campaign));
        Campaign createdCampaign = campaignRepository.save(campaign);
        saveCampaignHistory(createdCampaign);
    }

    public void saveCampaignHistory(Campaign campaign) {
        CampaignHistory campaignHistory = new CampaignHistory();
        campaignHistory.setCampaignId(campaign.getId());
        campaignHistory.setStatus(campaign.getStatus());
        campaignHistory.setModifiedDate(LocalDateTime.now());
        campaignHistoryRepository.save(campaignHistory);
    }

    public CampaignStatus setStatus(Campaign campaign) {
        CampaignStatus status;
        if (duplicateCampaign(campaign) == true) {
            status = CampaignStatus.DUPLICATE;
        } else if (campaign.getCategory().equals(CampaignCategory.HS)) {
            status = CampaignStatus.ACTIVE;
        } else {
            status = CampaignStatus.PENDING_APPROVAL;
        }
        return status;
    }

    public boolean duplicateCampaign(Campaign campaign) {
        boolean result;
        Campaign existCampaign = campaignRepository.findCampaignByCategoryAndTitleAndDescription(campaign.getCategory(), campaign.getTitle(), campaign.getDescription());

        if (existCampaign != null
                && existCampaign.getCategory() == campaign.getCategory()
                && existCampaign.getTitle().equals(campaign.getTitle())
                && existCampaign.getDescription().equals(campaign.getDescription())) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    public List<Campaign> getCampaign() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign id bulunamadı"));
    }

    public void activateCampaign(Long id) {
        Campaign campaign = getCampaignById(id);
        Optional.of(campaign)
                .filter(e -> e.getStatus().equals(CampaignStatus.PENDING_APPROVAL))
                .map(e -> {
                    e.setStatus(CampaignStatus.ACTIVE);
                    return e;
                }).orElseThrow(() -> new RuntimeException("Can not change status. Status: " + campaign.getStatus()));
        Campaign activatedCampaign = campaignRepository.save(campaign);
        saveCampaignHistory(activatedCampaign);
    }

    public void deactivateCampaign(Long id) {
        Campaign campaign = getCampaignById(id);
        Optional.of(campaign)
                .filter(e -> e.getStatus().equals(CampaignStatus.PENDING_APPROVAL) || e.getStatus().equals(CampaignStatus.ACTIVE))
                .map(e -> {
                    e.setStatus(CampaignStatus.DEACTIVE);
                    return e;
                }).orElseThrow(() -> new RuntimeException("Can not change status. Status: " + campaign.getStatus()));
        Campaign deactivatedCampaign = campaignRepository.save(campaign);
        saveCampaignHistory(deactivatedCampaign);
    }

}

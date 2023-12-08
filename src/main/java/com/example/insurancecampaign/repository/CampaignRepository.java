package com.example.insurancecampaign.repository;

import com.example.insurancecampaign.enums.CampaignCategory;
import com.example.insurancecampaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    Campaign findCampaignByCategoryAndTitleAndDescription(CampaignCategory category, String title, String description);
}

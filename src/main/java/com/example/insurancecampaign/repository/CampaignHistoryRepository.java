package com.example.insurancecampaign.repository;

import com.example.insurancecampaign.model.CampaignHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignHistoryRepository extends JpaRepository<CampaignHistory,Long> {
}

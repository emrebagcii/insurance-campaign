package com.example.insurancecampaign.model;

import com.example.insurancecampaign.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="campaign_history")
@Getter
@Setter
public class CampaignHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Enumerated(EnumType.STRING)
    @Column(name="campaign_status")
    private CampaignStatus status;

    @Column(name="modified_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime modifiedDate;
}

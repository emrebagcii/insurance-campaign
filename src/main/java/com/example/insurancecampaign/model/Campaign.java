package com.example.insurancecampaign.model;

import com.example.insurancecampaign.enums.CampaignCategory;
import com.example.insurancecampaign.enums.CampaignStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "campaign")
@Getter
@Setter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "campaign_id")
    private Long id;

    @Size(min = 10, max = 50, message = "Başlık en az 10 en fazla 50 karakter olabilir!!")
    @Column(name = "campaign_title")
    private String title;

    @Size(min = 20, max = 200, message = "Açıklama en az 20 en fazla 200 karakter olabilir!!")
    @Column(name = "campaign_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "campaign_category")
    private CampaignCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "campaign_status")
    private CampaignStatus status;
}

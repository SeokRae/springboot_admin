package com.erp.admin.model.network.response;

import com.erp.admin.model.status.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiResponse {

    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PartnerStatus status;

    private String address;

    private String callCenter;

    private String partnerNumber;

    private String businessNumber;

    private String ceoName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private Long categoryId;
    
}

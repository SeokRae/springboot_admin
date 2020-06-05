package com.erp.admin.service;

import com.erp.admin.model.entity.Partner;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.Pagination;
import com.erp.admin.model.network.request.PartnerApiRequest;
import com.erp.admin.model.network.response.PartnerApiResponse;
import com.erp.admin.model.status.PartnerStatus;
import com.erp.admin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartnerService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();
        Partner partner = Partner.builder()
                .name(body.getName())
                .status(PartnerStatus.REGISTERED)
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(body.getRegisteredAt())
                .unregisteredAt(body.getUnregisteredAt())
                .category(categoryRepository.getOne(body.getCategoryId()))
                .build();
        Partner newPartner = baseRepository.save(partner);

        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();

        return baseRepository.findById(partnerApiRequest.getId())
                .map(partner -> {
                    System.out.println(partnerApiRequest.getStatus());
                    partner
                            .setName(partnerApiRequest.getName())
                            .setStatus(partnerApiRequest.getStatus())
                            .setAddress(partnerApiRequest.getAddress())
                            .setCallCenter(partnerApiRequest.getCallCenter())
                            .setPartnerNumber(partnerApiRequest.getPartnerNumber())
                            .setBusinessNumber(partnerApiRequest.getBusinessNumber())
                            .setCeoName(partnerApiRequest.getCeoName())
                            .setRegisteredAt(partnerApiRequest.getRegisteredAt())
                            .setCategory(categoryRepository.getOne(partnerApiRequest.getCategoryId()));
                    return partner;
                })
                .map(updatedPartner -> baseRepository.save(updatedPartner))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 수정 오류"));
    }

    @Override
    public Header<?> delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 삭제 오류"));
    }

    @Override
    public Header<List<PartnerApiResponse>> search(Pageable pageable) {
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .currentOfElements(partners.getNumberOfElements())
                .currentPage(partners.getNumber())
                .totalElements(partners.getTotalElements())
                .totalPages(partners.getTotalPages())
                .build();

        return Header.OK(partnerApiResponseList, pagination);
    }


    private PartnerApiResponse response(Partner partner) {
        return PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();
    }
}

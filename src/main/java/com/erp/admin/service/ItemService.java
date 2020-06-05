package com.erp.admin.service;

import com.erp.admin.model.entity.Item;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.Pagination;
import com.erp.admin.model.network.request.ItemApiRequest;
import com.erp.admin.model.network.response.ItemApiResponse;
import com.erp.admin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();
        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();
        Item newItem = baseRepository.save(item);

        return Header.OK(response(newItem));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("아이템이 없습니다."));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
                .map(item -> {
                    item
                            .setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());
                    return item;
                })
                .map(newEntity -> baseRepository.save(newEntity))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음")); // 데이터 예외 처리
    }

    @Override
    public Header<?> delete(Long id) {
        return baseRepository.findById(id)
                .map(item -> {
                    baseRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentOfElements(items.getNumberOfElements())
                .build();
        return Header.OK(itemApiResponseList);
    }

    public ItemApiResponse response(Item item) {
        return ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
    }
}

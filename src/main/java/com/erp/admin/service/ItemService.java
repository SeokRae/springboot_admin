package com.erp.admin.service;

import com.erp.admin.controller.ItemAPIController;
import com.erp.admin.interfaces.CrudInterface;
import com.erp.admin.model.entity.Item;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.request.ItemAPIRequest;
import com.erp.admin.model.network.response.ItemAPIResponse;
import com.erp.admin.repository.ItemRepository;
import com.erp.admin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemService implements CrudInterface<ItemAPIRequest, ItemAPIResponse> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemAPIResponse> create(Header<ItemAPIRequest> request) {
        ItemAPIRequest body = request.getData();
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
        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemAPIResponse> read(Long id) {
        return itemRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("아이템이 없습니다."));
    }

    @Override
    public Header<ItemAPIResponse> update(Header<ItemAPIRequest> request) {
        ItemAPIRequest body = request.getData();
        return itemRepository.findById(body.getId())
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
                .map(newEntity -> itemRepository.save(newEntity)) // db로 update
                .map(this::response) // response로 Header + item 반환
                .orElseGet(()-> Header.ERROR("데이터 없음")); // 데이터 예외 처리
    }

    @Override
    public Header<?> delete(Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<ItemAPIResponse> response(Item item) {
        ItemAPIResponse body = ItemAPIResponse.builder()
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
        return Header.OK(body);
    }
}

package com.erp.admin.service;

import com.erp.admin.model.entity.OrderGroup;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.Pagination;
import com.erp.admin.model.network.request.OrderGroupApiRequest;
import com.erp.admin.model.network.response.OrderGroupApiResponse;
import com.erp.admin.model.status.OrderStatus;
import com.erp.admin.repository.UserRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderGroupService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(OrderStatus.ORDERING)
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(body.getOrderAt())
                .arrivalDate(body.getArrivalDate())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);
        return Header.OK(response(newOrderGroup));
    }


    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없어서 오류"));

    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
                .map(orderGroup -> {
                    orderGroup
                            .setStatus(body.getStatus())
                            .setOrderType(body.getOrderType())
                            .setRevName(body.getRevName())
                            .setRevAddress(body.getRevAddress())
                            .setPaymentType(body.getPaymentType())
                            .setTotalPrice(body.getTotalPrice())
                            .setTotalQuantity(body.getTotalQuantity())
                            .setOrderAt(body.getOrderAt())
                            .setArrivalDate(body.getArrivalDate())
                            .setUser(userRepository.getOne(body.getUserId()));
                    return orderGroup;
                })
                .map(updatedOrderGroup -> baseRepository.save(updatedOrderGroup))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 수정 오류"));

    }

    @Override
    public Header<?> delete(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 삭제 오류"));
    }

    @Override
    public Header<List<OrderGroupApiResponse>> search(Pageable pageable) {
        Page<OrderGroup> orderGroups = baseRepository.findAll(pageable);

        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroups.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(orderGroups.getTotalPages())
                .totalElements(orderGroups.getTotalElements())
                .currentPage(orderGroups.getNumber())
                .currentOfElements(orderGroups.getNumberOfElements())
                .build();

        return Header.OK(orderGroupApiResponseList);
    }

    public OrderGroupApiResponse response(OrderGroup orderGroup) {
        return OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getId())
                .build();
    }
}

package com.erp.admin.service;

import com.erp.admin.model.entity.Item;
import com.erp.admin.model.entity.OrderDetail;
import com.erp.admin.model.entity.OrderGroup;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.request.OrderDetailApiRequest;
import com.erp.admin.model.network.response.OrderDetailApiResponse;
import com.erp.admin.model.network.response.OrderGroupApiResponse;
import com.erp.admin.repository.ItemRepository;
import com.erp.admin.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderDetailService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupService orderGroupService;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();
        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .arrivalDate(body.getArrivalDate())
                .item(itemRepository.getOne(body.getItemId()))
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);
        return Header.OK(response(newOrderDetail));
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        // 주문 상세 데이터 가져와야 함

        // 1. orderGroup의 quantity

        // 2. item의 price 가져오기

        // 3. price.multiply(quantity)
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    OrderGroup orderGroup = orderGroupRepository.getOne(orderDetail.getOrderGroup().getId());
                    Item item = itemRepository.getOne(orderDetail.getItem().getId());
                    int quantity = orderGroup.getTotalQuantity();

                    orderDetail.setQuantity(quantity);
                    orderDetail.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(quantity)));

                    return orderDetail;
                })
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        return baseRepository.findById(orderDetailApiRequest.getId())
                .map(orderDetail -> {
                    orderDetail
                            .setStatus(orderDetailApiRequest.getStatus())
                            .setQuantity(orderDetailApiRequest.getQuantity())
                            .setTotalPrice(orderDetailApiRequest.getTotalPrice())
                            .setArrivalDate(orderDetailApiRequest.getArrivalDate());

                    return orderDetail;
                })
                .map(orderDetail -> baseRepository.save(orderDetail))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 수정 오류"));
    }

    @Override
    public Header<?> delete(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 삭제 실패"));
    }

    @Override
    public Header<List<OrderDetailApiResponse>> search(Pageable pageable) {
        return null;
    }

    private OrderDetailApiResponse response(OrderDetail orderDetail) {
        return OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .arrivalDate(orderDetail.getArrivalDate())
                .itemId(orderDetail.getItem().getId())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .build();
    }
}

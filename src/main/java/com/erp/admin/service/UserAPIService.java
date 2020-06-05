package com.erp.admin.service;

import com.erp.admin.model.entity.OrderDetail;
import com.erp.admin.model.entity.OrderGroup;
import com.erp.admin.model.entity.User;
import com.erp.admin.model.network.Pagination;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.request.UserApiRequest;
import com.erp.admin.model.network.response.ItemApiResponse;
import com.erp.admin.model.network.response.OrderGroupApiResponse;
import com.erp.admin.model.network.response.UserApiResponse;
import com.erp.admin.model.network.response.UserOrderInfoApiResponse;
import com.erp.admin.model.status.UserStatus;
import com.erp.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAPIService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderGroupService orderGroupService;

    @Autowired
    private ItemService itemService;
    /**
     *  1. request data
     *  2. create user
     *  3. create user response data
     * @param userAPIRequest controller로부터 전달 받은 Json 타입의 userAPIRequest
     * @return controller로부터 request 값을 전달받아 User Entity와 매핑하여 save한 결과 값을 response
     */
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> userAPIRequest) {
        UserApiRequest request = userAPIRequest.getData();

        User user = User.builder()
                .account(request.getAccount())
                .password(request.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUSer = baseRepository.save(user);

        return Header.OK(response(newUSer));
    }

    /**
     * 1. id -> repository getOne, getById
     * 2. user -> userRepository return
     * @param id DB로부터 호출 할 데이터의 pk 값
     * @return 데이터를 호출하여 반환 할 Header + userAPIResponse
     */
    @Override
    public Header<UserApiResponse> read(Long id) {
        Optional<User> optional = baseRepository.findById(id);

        return optional
                .map(this::response)
                .map(Header::OK)
                .orElseGet(
                () -> Header.ERROR("데이터 없음")
        );
    }

    /**
     * 1. data
     * 2. id -> search user
     * 3. update
     * 4. userAPIResponse
     * @param userAPIRequest controller로부터 전달 받은 Json 타입의 userAPIRequest
     * @return 데이터가 수정된 후 반환 할 Header + userAPIResponse 데이터
     */
    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> userAPIRequest) {
        UserApiRequest request = userAPIRequest.getData();

        return baseRepository.findById(request.getId())
                .map(user -> {
                    user.setAccount(request.getAccount())
                            .setEmail(request.getEmail())
                            .setStatus(request.getStatus()) // Enum으로 설계 했지만 update 시 오류가 발생 할 수 있음
                            .setPassword(request.getPassword())
                            .setPhoneNumber(request.getPhoneNumber())
                            .setRegisteredAt(request.getRegisteredAt())
                            .setUnregisteredAt(request.getUnregisteredAt());
                    return user;
                })
                .map(user -> baseRepository.save(user)) // update
                .map(this::response) // lambda 코드 updatedUser를 response 메서드에 전달하여 Header<UserAPIResponse>로 반환
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    /**
     * 1. id -> repository -> user
     * 2. repository -> delete
     * 3. response return
     * @param id delete 할 데이터의 pk 값
     * @return 데이터가 삭제 될 때 성공 또는 실패의 응답 값
     */
    @Override
    public Header<?> delete(Long id) {
        Optional<User> optional = baseRepository.findById(id);

        return optional
                .map(user -> {
                    baseRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 삭제 실패"));
    }

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponseList = users.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentOfElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList, pagination);
    }

    /**
     * 1. user Entity를 전달 받음
     * 2. user -> userAPIResponse mapping
     * 3. header + userAPIResponse
     * @param user CRU Method에서 각 작업된 user entity를 전달받을 파라미터
     * @return user Entity를  UserAPIResponse 로 변경하여 Header 값을 포함하여 리턴
     */
    private UserApiResponse response(User user) {
        // user -> userAPIResponse
        return UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .status(user.getStatus())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        // user

        User user = userRepository.getOne(id);
        UserApiResponse userAPIResponse = response(user);

        // orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupAPIResponse = orderGroupService.response(orderGroup);
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(OrderDetail::getItem)
                            .map(item -> itemService.response(item))
                            .collect(Collectors.toList());
                    orderGroupAPIResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupAPIResponse;
                })
                .collect(Collectors.toList());
        userAPIResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
        UserOrderInfoApiResponse userOrderInfoAPIResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userAPIResponse)
                .build();
        return Header.OK(userOrderInfoAPIResponse);
    }
}

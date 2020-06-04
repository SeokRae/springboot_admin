package com.erp.admin.service;

import com.erp.admin.interfaces.CrudInterface;
import com.erp.admin.model.entity.User;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.request.UserAPIRequest;
import com.erp.admin.model.network.response.UserAPIResponse;
import com.erp.admin.repository.UserRepository;
import net.bytebuddy.description.NamedElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAPIService implements CrudInterface<UserAPIRequest, UserAPIResponse> {

    @Autowired
    private UserRepository userRepository;

    /**
     *  1. request data
     *  2. create user
     *  3. create user response data
     * @param userAPIRequest controller로부터 전달 받은 Json 타입의 userAPIRequest
     * @return controller로부터 request 값을 전달받아 User Entity와 매핑하여 save한 결과 값을 response
     */
    @Override
    public Header<UserAPIResponse> create(Header<UserAPIRequest> userAPIRequest) {
        UserAPIRequest request = userAPIRequest.getData();

        User user = User.builder()
                .account(request.getAccount())
                .password(request.getPassword())
                .status("REGISTERED")
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUSer = userRepository.save(user);

        return response(newUSer);
    }

    /**
     * 1. id -> repository getOne, getById
     * 2. user -> userRepository return
     * @param id DB로부터 호출 할 데이터의 pk 값
     * @return 데이터를 호출하여 반환 할 Header + userAPIResponse
     */
    @Override
    public Header<UserAPIResponse> read(Long id) {
        Optional<User> optional = userRepository.findById(id);

        return optional.map(
                this::response
        ).orElseGet(
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
    public Header<UserAPIResponse> update(Header<UserAPIRequest> userAPIRequest) {
        UserAPIRequest request = userAPIRequest.getData();

        Optional<User> optional = userRepository.findById(request.getId());

        return optional
                .map(user -> {
                    user.setAccount(request.getAccount())
                            .setEmail(request.getEmail())
                            .setStatus(request.getStatus())
                            .setPassword(request.getPassword())
                            .setPhoneNumber(request.getPhoneNumber())
                            .setRegisteredAt(request.getRegisteredAt())
                            .setUnregisteredAt(request.getUnregisteredAt());
                    return user;
                })
                .map(user -> userRepository.save(user)) // update
                .map(this::response) // lambda 코드 updatedUser를 response 메서드에 전달하여 Header<UserAPIResponse>로 반환
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
        Optional<User> optional = userRepository.findById(id);

        return optional
                .map(user -> {
                    userRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 삭제 실패"));
    }

    /**
     * 1. user Entity를 전달 받음
     * 2. user -> userAPIResponse mapping
     * 3. header + userAPIResponse
     * @param user CRU Method에서 각 작업된 user entity를 전달받을 파라미터
     * @return user Entity를  UserAPIResponse 로 변경하여 Header 값을 포함하여 리턴
     */
    private Header<UserAPIResponse> response(User user) {
        // user -> userAPIResponse
        UserAPIResponse userAPIResponse = UserAPIResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .status(user.getStatus())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + UserAPIResponse
        return Header.OK(userAPIResponse);
    }
}

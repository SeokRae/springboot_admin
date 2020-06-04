package com.erp.admin.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신 시간
    private LocalDateTime transactionTime;
    // api 결과 코드
    private String resultCode;
    // api 부가 설명
    private String description;

    private T data;

    /**
     * OK
     * @param <T> Header에 담을 임의 Data 타입
     * @return 성공적인 결과 값을 반환하는 Header
     */
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    /**
     * Data OK
     * @param data API 반환할 시 데이터 부분
     * @param <T> Header에 담을 임의 Data 타입
     * @return Header + Data 를 포함하여 반환
     */
    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    /**
     * 오류 처리
     * @param description 오류 내용을 받을 파라미터
     * @param <T> Header에 담을 임의 Data 타입
     * @return Header + 특정 오류 설명을 포함하여 반환
     */
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}

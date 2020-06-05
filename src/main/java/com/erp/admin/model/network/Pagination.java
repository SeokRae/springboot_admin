package com.erp.admin.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {

    private Long totalElements;

    private int totalPages;

    private int currentPage;

    private int currentOfElements;
}

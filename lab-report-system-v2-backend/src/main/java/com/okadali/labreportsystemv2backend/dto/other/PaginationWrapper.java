package com.okadali.labreportsystemv2backend.dto.other;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationWrapper<T> {

    int totalPages;
    int currentPage;
    int numberOfContent;
    T content;

}

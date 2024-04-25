package com.foureve.labmanagementbackend.domain.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestInfo {

    private Long userId;

    private String token;

    private String semester;
}

package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateAccountRequestDTO {
    private Double initialBalance;
    private String currency;
}
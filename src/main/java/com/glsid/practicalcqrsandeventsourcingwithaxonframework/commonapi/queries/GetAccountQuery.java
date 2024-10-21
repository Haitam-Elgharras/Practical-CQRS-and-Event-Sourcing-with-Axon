package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetAccountQuery {
    private final String id;

}

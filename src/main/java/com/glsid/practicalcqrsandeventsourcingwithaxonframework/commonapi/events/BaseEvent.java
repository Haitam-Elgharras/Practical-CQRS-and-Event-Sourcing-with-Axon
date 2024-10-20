package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events;

import lombok.Getter;

public abstract class BaseEvent<T>  {
    @Getter
    public final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}

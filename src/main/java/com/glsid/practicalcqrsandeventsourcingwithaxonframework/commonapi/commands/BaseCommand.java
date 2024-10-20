package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Getter
public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier // we use this annotation to indicate that this field is the identifier of the aggregate
    private T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
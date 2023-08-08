package com.vincent.assessment.util;

import com.vincent.assessment.model.Money;
import com.vincent.assessment.persistance.entity.MoneyEntity;
import java.util.ArrayList;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-08T23:28:21+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class MoneyMappersImpl implements MoneyMappers {

    @Override
    public Money map(MoneyEntity source) {

        Money money = new Money();

        if ( source != null ) {
            money.setId( source.getId() );
            money.setDenomination( source.getDenomination() );
            money.setAmount( source.getAmount() );
        }

        return money;
    }

    @Override
    public Iterable<Money> map(Iterable<MoneyEntity> source) {
        if ( source == null ) {
            return new ArrayList<Money>();
        }

        ArrayList<Money> iterable = new ArrayList<Money>();
        for ( MoneyEntity moneyEntity : source ) {
            iterable.add( map( moneyEntity ) );
        }

        return iterable;
    }
}

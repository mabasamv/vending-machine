package com.vincent.assessment.util;

import com.vincent.assessment.model.PettyCash;
import com.vincent.assessment.model.PettyCash.PettyCashBuilder;
import com.vincent.assessment.persistance.entity.PettyCashEntity;
import java.util.ArrayList;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-13T17:07:42+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
public class PettyCashMappersImpl implements PettyCashMappers {

    @Override
    public PettyCash map(PettyCashEntity source) {

        PettyCashBuilder pettyCash = PettyCash.builder();

        if ( source != null ) {
            pettyCash.id( source.getId() );
            pettyCash.denomination( source.getDenomination() );
            pettyCash.totalAmount( source.getTotalAmount() );
        }

        return pettyCash.build();
    }

    @Override
    public PettyCashEntity map(PettyCash source) {

        PettyCashEntity pettyCashEntity = new PettyCashEntity();

        if ( source != null ) {
            pettyCashEntity.setId( source.getId() );
            pettyCashEntity.setDenomination( source.getDenomination() );
            pettyCashEntity.setTotalAmount( source.getTotalAmount() );
        }

        return pettyCashEntity;
    }

    @Override
    public Iterable<PettyCash> map(Iterable<PettyCashEntity> source) {
        if ( source == null ) {
            return new ArrayList<PettyCash>();
        }

        ArrayList<PettyCash> iterable = new ArrayList<PettyCash>();
        for ( PettyCashEntity pettyCashEntity : source ) {
            iterable.add( map( pettyCashEntity ) );
        }

        return iterable;
    }
}
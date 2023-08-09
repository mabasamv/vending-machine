package com.vincent.assessment.util;

import com.vincent.assessment.model.Change;
import com.vincent.assessment.persistance.entity.ChangeEntity;
import java.util.ArrayList;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-09T09:58:18+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class ChangeMappersImpl implements ChangeMappers {

    @Override
    public Change map(ChangeEntity source) {

        Change change = new Change();

        if ( source != null ) {
            change.setId( source.getId() );
            change.setDenomination( source.getDenomination() );
            change.setTotalAmount( source.getTotalAmount() );
        }

        return change;
    }

    @Override
    public ChangeEntity map(Change source) {

        ChangeEntity changeEntity = new ChangeEntity();

        if ( source != null ) {
            changeEntity.setId( source.getId() );
            changeEntity.setDenomination( source.getDenomination() );
            changeEntity.setTotalAmount( source.getTotalAmount() );
        }

        return changeEntity;
    }

    @Override
    public Iterable<Change> map(Iterable<ChangeEntity> source) {
        if ( source == null ) {
            return new ArrayList<Change>();
        }

        ArrayList<Change> iterable = new ArrayList<Change>();
        for ( ChangeEntity changeEntity : source ) {
            iterable.add( map( changeEntity ) );
        }

        return iterable;
    }
}

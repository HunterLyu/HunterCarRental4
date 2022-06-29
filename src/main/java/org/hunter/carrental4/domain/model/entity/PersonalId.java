package org.hunter.carrental4.domain.model.entity;

import lombok.Data;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;

@Data
public class PersonalId {
    private String number;
    private PersonalIdIdType personalIdIdType;
}

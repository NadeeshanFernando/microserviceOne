package com.anton.microOne.dto;

import lombok.*;

/**
 * @author by nadeeshan_fdz
 */
@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContactDto {

    public long contactId;
    public String mobile;

    public String remark;
    public long employeeId;
}

package com.kyo.springbootstart.process.base.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class Person {

    private Long id;

    private String name;

}

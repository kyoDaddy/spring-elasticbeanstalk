package com.kyo.springbootstart.process.base.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter @Setter
@ToString
@Builder
public class HateoasSample extends RepresentationModel<HateoasSample> {

    private String prefix;

    private String name;

}

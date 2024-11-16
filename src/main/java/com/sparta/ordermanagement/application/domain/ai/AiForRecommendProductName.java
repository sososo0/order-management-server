package com.sparta.ordermanagement.application.domain.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AiForRecommendProductName{

    String userStringId;
    String requestContent;
    String responseContent;

    public static AiForRecommendProductName from(AiForRecommendProductName presentDomain, String responseContent){
        return new AiForRecommendProductName(
                presentDomain.getUserStringId(),
                presentDomain.getRequestContent(),
                responseContent
        );
    }
}

package com.sparta.ordermanagement.bootstrap.rest.dto.ai;

import com.sparta.ordermanagement.application.domain.ai.AiForRecommendProductName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AiRecommendProductNameRequest(

        @NotNull(message = "요청된 내용이 없습니다.")
        @Size(max = 20, message = "20자 이하로 요청해야합니다.")
        String requestContent
) {

        public AiForRecommendProductName toDomain(String userStringId){
                return new AiForRecommendProductName(
                        userStringId,
                        this.requestContent,
                        ""
                );

        }
}

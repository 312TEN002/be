package com.be.bloom.service;

import com.be.bloom.dto.ClovaRequestDto.ClovaRequest;
import com.be.bloom.dto.ClovaRequestDto.ClovaRequestMessage;
import com.be.bloom.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    @Transactional(readOnly = true)
    public String getTestData() {
        return testMapper.getTestData();
    }

    @Transactional
    public String postTest() {
        RestTemplate template = new RestTemplate();

        // request data
        ArrayList<ClovaRequestMessage> messageList = new ArrayList();
        messageList.add(new ClovaRequestMessage("system", "항목을 입력하면 대상자에게 전달할 \\b결혼식 축사 문장을 추천 해드립니다.\\n(지정한 대상자의 이름과 지정한 축사자의 이름이 내용에 들어가야 함)"));
        messageList.add(new ClovaRequestMessage("user", "- 대상자: 신부\\n- 대상자 이름: 손다현\\n- 대상자와 나(축사자)의 관계: 친구\\n- 축사자 이름: 송유진\\n- 축사 분위기: 재미\\n- 축사에 들어갈 스토리텔링: \\b고등학교 때 처음 알게 된 사이이고 다현이네 집에서 김치볶음밥을 먹은 기억이 있음.\\n- 말투: 반말\\n- 마지막으로 해주고 싶은 말: 행복하게 잘살아라\\n- 최소 글자수: 500자\\n- 최대 글자수: 1000자 (문장은 제대로 끝마치도록)"));

        ClovaRequest clovaRequest = ClovaRequest.builder()
                .messages(messageList)
                .topP(0.8)
                .topK(0)
                .maxTokens(2000)
                .temperature(0.5)
                .repeatPenalty(5.0)
                .stopBefore(new ArrayList<>())
                .includeAiFilters(true)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", "NTA0MjU2MWZlZTcxNDJiYzQNPMHi/Vt8f/jUw+uD7CxTg8NfmaRlPXQoigyeublY7eVnylTxPL1vEyeFjZDwRkkqeiMSeAXfdB1q9+QTAs9Z4BA6CjvC9odNIFkcQ2l6GU2dPPD8l70WoKJ7nOewsdU6Key8/+plx3IVU/6g6hv0JXRjZf89oC384tCoyzfO+IcQ6Mjw00pJLaaBRSr53bQ0lNhdj3GWf4bDr7u1aV0=");
        headers.set("X-NCP-APIGW-API-KEY", "CNRSgHB8UsB0ReR29fU3NqjG8XRfWvifgKOhu6JY");
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", "20b7dae169554243b9c6c44cd718a309");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity formEntity = new HttpEntity<>(clovaRequest, headers);

        String apiUrl = "https://clovastudio.stream.ntruss.com/testapp/v1/chat-completions/HCX-002";

        LinkedHashMap resMap = template.postForObject(apiUrl, formEntity, LinkedHashMap.class);

        String content = (String) ((LinkedHashMap)((LinkedHashMap)resMap.get("result")).get("message")).get("content");

        /* 데이터 후처리 */
        // 1. [축사자 이름] replace


        System.out.println(content);

        return content;
    }
}

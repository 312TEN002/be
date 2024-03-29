package com.be.bloom.dto.ClovaRequestDto;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class ClovaStudyRequest {

    private String name;
    private String model;
    private String method;
    private String taskType;
    private String trainEpochs;
    private String learningRate;
    private File trainingDataset;

}
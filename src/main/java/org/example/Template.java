package org.example;

import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

public class Template {

    static void createTemplate(QuickSightClient client) {

        CreateTemplateRequest createTemplateRequest = CreateTemplateRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .templateId("template_sdk")
                .name("template_sdk")
                .sourceEntity(sourceEntity())
                .build();

        CreateTemplateResponse response = client.createTemplate(createTemplateRequest);

        System.out.println(response.status());
    }

    private static TemplateSourceEntity sourceEntity() {

        return TemplateSourceEntity.builder()
                .sourceAnalysis(TemplateSourceAnalysis.builder()
                                    .arn("arn:aws:quicksight:us-east-1:661410064369:analysis/analysis1_sdk")
                                    .dataSetReferences(DataSetReference.builder()
                                                            .dataSetPlaceholder("aaa")
                                                            .dataSetArn("arn:aws:quicksight:us-east-1:661410064369:dataset/dataset03sdk")
                                                            .build())
                                    .build())
                .build();
    }
}

package org.example;

import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

public class DataSource2 {

    
    public static void createDataSource2(QuickSightClient client) {

        ManifestFileLocation manifestFileLocation = ManifestFileLocation.builder()
                .bucket("quicksight-example-2023-04")
                .key("manifest04/manifest")
                .build();

        S3Parameters s3Parameters = S3Parameters.builder()
                .manifestFileLocation(manifestFileLocation)
                .build();

        DataSourceParameters dataSourceParameters = DataSourceParameters.builder()
                .s3Parameters(s3Parameters)
                .build();

        CreateDataSourceRequest createDataSourceRequest = CreateDataSourceRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .dataSourceId("04sdk")
                .name("04sdk")
                .type("S3")
                .dataSourceParameters(dataSourceParameters)
                .build();

        CreateDataSourceResponse dataSource = client.createDataSource(createDataSourceRequest);

        System.out.println(dataSource.creationStatusAsString());
    }
}
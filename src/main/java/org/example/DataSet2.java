package org.example;

import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

import java.util.HashMap;
import java.util.Map;

public class DataSet2 {

    public static void createDataset2(QuickSightClient client) {

        InputColumn a = InputColumn.builder().name("Name").type("STRING").build();
        InputColumn b = InputColumn.builder().name("Age").type("STRING").build();
        InputColumn c = InputColumn.builder().name("Weight").type("STRING").build();

        S3Source s3Source = S3Source.builder()
                .dataSourceArn("arn:aws:quicksight:us-east-1:661410064369:datasource/04sdk")
                .inputColumns(a, b, c)
                .build();

        Map<String, PhysicalTable> map = new HashMap<String, PhysicalTable>();
        map.put("KeyName", PhysicalTable.builder().s3Source(s3Source).build());

        CreateDataSetRequest createDataSetRequest = CreateDataSetRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .dataSetId("dataset04sdk")
                .name("dataset04sdk")
                .importMode(DataSetImportMode.SPICE)
                .physicalTableMap(map)
                .build();

        CreateDataSetResponse response = client.createDataSet(createDataSetRequest);

        System.out.println(response.status());
    }
}
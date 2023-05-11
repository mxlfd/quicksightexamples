package org.example;

import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

import java.util.HashMap;
import java.util.Map;

public class DataSet {
    public static void createDataset1(QuickSightClient client) {

        InputColumn a = InputColumn.builder().name("Name").type("STRING").build();
        InputColumn b = InputColumn.builder().name("Age").type("STRING").build();

        S3Source s3Source = S3Source.builder()
                .dataSourceArn("arn:aws:quicksight:us-east-1:661410064369:datasource/03sdk")
                .inputColumns(a, b)
                .build();

        Map<String, PhysicalTable> map = new HashMap<String, PhysicalTable>();
        map.put("KeyName", PhysicalTable.builder().s3Source(s3Source).build());

        CreateDataSetRequest createDataSetRequest = CreateDataSetRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .dataSetId("dataset03sdk")
                .name("dataset03sdk")
                .importMode(DataSetImportMode.SPICE)
                .physicalTableMap(map)
                .build();

        CreateDataSetResponse dataSet = client.createDataSet(createDataSetRequest);
        System.out.println(dataSet.status());

    }
}
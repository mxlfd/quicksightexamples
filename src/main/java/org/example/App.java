package org.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.quicksight.QuickSightClient;

import java.io.IOException;

public class App {

    public static final String AWS_ACCOUNT_ID = "661410064369";

    public static void main(String[] args) throws IOException {

        QuickSightClient client = QuickSightClient.builder().region(Region.US_EAST_1).build();

        // createDataSource1(client);
        // CREATION_SUCCESSFUL, permissions must be edited to display in UI

        // createDataset1(client);
        // appears in cli, permissions must be edited to display in UI

        // Analysis.createAnalysis(client);

        // Analysis.updateAnalysisPermissions(client);

        // Template.createTemplate();

        // Dashboard.createDashboard(client);

        // Dashboard.updateDashboardPermissions(client);


        // DataSource2.createDataSource2(client);

        // DataSet2.createDataset2(client);

        // AnalysisUpdate.updateAnalysis(client);

    }

}

package org.example;

import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

public class Dashboard {

    static void createDashboard(QuickSightClient client) {

        CreateDashboardRequest createDashboardRequest = CreateDashboardRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .dashboardId("dashboard_sdk")
                .name("dashboard_sdk")
                .sourceEntity(dashboardSourceEntity())
                .build();

        CreateDashboardResponse response = client.createDashboard(createDashboardRequest);

        System.out.println(response.status());
    }

    private static DashboardSourceEntity dashboardSourceEntity() {
        return DashboardSourceEntity.builder()
                .sourceTemplate(DashboardSourceTemplate.builder()
                                    .arn("arn:aws:quicksight:us-east-1:661410064369:template/template_cli")
                                    .dataSetReferences(DataSetReference.builder()
                                                            .dataSetArn("arn:aws:quicksight:us-east-1:661410064369:dataset/dataset03sdk")
                                                            .dataSetPlaceholder("aaa")
                                                            .build())
                                    .build())
                .build();
    }


    public static void updateDashboardPermissions(QuickSightClient client) {
        ResourcePermission p1 = ResourcePermission.builder()
                .principal("arn:aws:quicksight:us-east-1:661410064369:user/default/maxlaufeld@hotmail.com")
                .actions("quicksight:DescribeDashboard",
                        "quicksight:ListDashboardVersions",
                        "quicksight:QueryDashboard")
                .build();

        UpdateDashboardPermissionsRequest updateDashboardPermissionsRequest = UpdateDashboardPermissionsRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .dashboardId("dashboard_sdk")
                .grantPermissions(p1)
                .build();

        UpdateDashboardPermissionsResponse response = client.updateDashboardPermissions(updateDashboardPermissionsRequest);
        System.out.println(response.status());
    }
}

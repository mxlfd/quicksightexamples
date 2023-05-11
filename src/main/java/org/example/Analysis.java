package org.example;

import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

public class Analysis {

    static void createAnalysis(QuickSightClient client) {

        CreateAnalysisRequest createAnalysisRequest = CreateAnalysisRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .analysisId("analysis1_sdk")
                .name("analysis1_sdk")
                .definition(analysisDefinition())
                .build();

        CreateAnalysisResponse response = client.createAnalysis(createAnalysisRequest);

        System.out.println(response.status());
    }

    private static AnalysisDefinition analysisDefinition() {

        return AnalysisDefinition.builder()
                .dataSetIdentifierDeclarations(datasetIdentifierDeclarations())
                .sheets(sheetDefinition())
                .columnConfigurations(columnConfigurations())
                .analysisDefaults(analysisDefaults())
                .build();
    }

    private static DataSetIdentifierDeclaration datasetIdentifierDeclarations() {
        return DataSetIdentifierDeclaration.builder()
                .identifier("aaa")
                .dataSetArn("arn:aws:quicksight:us-east-1:661410064369:dataset/dataset03sdk")
                .build();
    }

    private static SheetDefinition sheetDefinition() {
        return SheetDefinition.builder()
                .sheetId("analysis1_sdk_sheet1")
                .name("analysis1_sdk_sheet1")
                .visuals(visuals())
                .layouts(layouts())
                .contentType(SheetContentType.INTERACTIVE)
                .build();
    }

    private static Visual visuals() {
        return Visual.builder()
                .tableVisual(TableVisual.builder()
                        .visualId("analysis1_sdk_sheet1_visual1")
                        .title(VisualTitleLabelOptions.builder()
                                .visibility(Visibility.VISIBLE)
                                .build())
                        .subtitle(VisualSubtitleLabelOptions.builder()
                                .visibility(Visibility.VISIBLE)
                                .build())
                        .chartConfiguration(chartConfiguration())
                        .build())
                .build();
    }

    private static TableConfiguration chartConfiguration() {
        return TableConfiguration.builder()
                .fieldWells(TableFieldWells.builder()
                        .tableUnaggregatedFieldWells(tableUnaggregatedFieldWells())
                        .build())
                .sortConfiguration(TableSortConfiguration.builder().build())
                .build();
    }

    private static TableUnaggregatedFieldWells tableUnaggregatedFieldWells() {
        UnaggregatedField unaggregatedField1 = UnaggregatedField.builder()
                .fieldId("field1")
                .column(ColumnIdentifier.builder()
                        .dataSetIdentifier("aaa")
                        .columnName("Name")
                        .build())

                .build();
        UnaggregatedField unaggregatedField2 = UnaggregatedField.builder()
                .fieldId("field2")
                .column(ColumnIdentifier.builder()
                        .dataSetIdentifier("aaa")
                        .columnName("Age")
                        .build())
                .build();
        return TableUnaggregatedFieldWells.builder()
                .values(unaggregatedField1, unaggregatedField2)
                .build();
    }

    private static Layout layouts() {
        return Layout.builder()
                .configuration(configuration())
                .build();
    }

    private static LayoutConfiguration configuration() {
        return LayoutConfiguration.builder()
                .gridLayout(GridLayoutConfiguration.builder()
                        .elements(GridLayoutElement.builder()
                                .elementId("analysis1_sdk_sheet1_visual1")
                                .elementType(LayoutElementType.VISUAL)
                                .columnIndex(0)
                                .columnSpan(6)
                                .rowIndex(0)
                                .rowSpan(4)
                                .build())
                        .canvasSizeOptions(GridLayoutCanvasSizeOptions.builder()
                                .screenCanvasSizeOptions(GridLayoutScreenCanvasSizeOptions.builder()
                                        .resizeOption(ResizeOption.FIXED)
                                        .optimizedViewPortWidth("1600px")
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private static ColumnConfiguration columnConfigurations() {
        ColumnIdentifier columnIdentifier = ColumnIdentifier.builder()
                .dataSetIdentifier("aaa")
                .columnName("Name")
                .build();

        return ColumnConfiguration.builder()
                .column(columnIdentifier)
                .role(ColumnRole.MEASURE)
                .build();
    }

    private static AnalysisDefaults analysisDefaults() {
        GridLayoutScreenCanvasSizeOptions gridLayoutScreenCanvasSizeOptions = GridLayoutScreenCanvasSizeOptions.builder()
                .resizeOption(ResizeOption.FIXED)
                .optimizedViewPortWidth("1600px")
                .build();

        GridLayoutCanvasSizeOptions gridLayoutCanvasSizeOptions = GridLayoutCanvasSizeOptions.builder()
                .screenCanvasSizeOptions(gridLayoutScreenCanvasSizeOptions)
                .build();

        DefaultGridLayoutConfiguration defaultGridLayoutConfiguration = DefaultGridLayoutConfiguration.builder()
                .canvasSizeOptions(gridLayoutCanvasSizeOptions)
                .build();

        DefaultInteractiveLayoutConfiguration defaultInteractiveLayoutConfiguration = DefaultInteractiveLayoutConfiguration.builder()
                .grid(defaultGridLayoutConfiguration)
                .build();

        DefaultNewSheetConfiguration defaultNewSheetConfiguration = DefaultNewSheetConfiguration.builder()
                .interactiveLayoutConfiguration(defaultInteractiveLayoutConfiguration)
                .build();

        AnalysisDefaults analysisDefaults = AnalysisDefaults.builder()
                .defaultNewSheetConfiguration(defaultNewSheetConfiguration)
                .build();

        return analysisDefaults;
    }

    public static void updateAnalysisPermissions(QuickSightClient client) {
        ResourcePermission p1 = ResourcePermission.builder()
                .principal("arn:aws:quicksight:us-east-1:661410064369:user/default/mlaufeld")
                .actions("quicksight:RestoreAnalysis",
                        "quicksight:UpdateAnalysisPermissions",
                        "quicksight:DeleteAnalysis",
                        "quicksight:DescribeAnalysisPermissions",
                        "quicksight:QueryAnalysis",
                        "quicksight:DescribeAnalysis",
                        "quicksight:UpdateAnalysis")
                .build();

        UpdateAnalysisPermissionsRequest updateAnalysisPermissionsRequest = UpdateAnalysisPermissionsRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .analysisId("analysis1_sdk")
                .grantPermissions(p1)
                .build();

        UpdateAnalysisPermissionsResponse response = client.updateAnalysisPermissions(updateAnalysisPermissionsRequest);
        System.out.println(response.status());
    }
}
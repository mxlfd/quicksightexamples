package org.example;

import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

import java.util.ArrayList;
import java.util.List;

public class AnalysisUpdate {

    static void updateAnalysis(QuickSightClient client) {

        DescribeAnalysisDefinitionRequest describeAnalysisDefinitionRequestOld = DescribeAnalysisDefinitionRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .analysisId("analysis1_sdk")
                .build();
        DescribeAnalysisDefinitionResponse response = client.describeAnalysisDefinition(describeAnalysisDefinitionRequestOld);
        AnalysisDefinition analysisDefinitionOld = response.definition();

        AnalysisDefinition analysisDefinitionNew = analysisDefinition(analysisDefinitionOld);

        //

        UpdateAnalysisRequest updateAnalysisRequest = UpdateAnalysisRequest.builder()
                .awsAccountId(App.AWS_ACCOUNT_ID)
                .analysisId("analysis1_sdk")
                .name("analysis1_sdk")
                .definition(analysisDefinitionNew)
                .build();

        UpdateAnalysisResponse updateAnalysisResponse = client.updateAnalysis(updateAnalysisRequest);

        System.out.println(updateAnalysisResponse.status());
    }



    private static AnalysisDefinition analysisDefinition(AnalysisDefinition analysisDefinitionOld) {

        List<DataSetIdentifierDeclaration> dataSetIdentifierDeclarationsOld = analysisDefinitionOld.dataSetIdentifierDeclarations();
        List<SheetDefinition> sheetsOld = analysisDefinitionOld.sheets();
        List<ColumnConfiguration> columnConfigurationsOld = analysisDefinitionOld.columnConfigurations();


        return AnalysisDefinition.builder()
                .dataSetIdentifierDeclarations(datasetIdentifierDeclarations(dataSetIdentifierDeclarationsOld))
                .sheets(sheetDefinition(sheetsOld))
                .columnConfigurations(columnConfigurations(columnConfigurationsOld))
                .build();
    }



    private static List<DataSetIdentifierDeclaration> datasetIdentifierDeclarations(List<DataSetIdentifierDeclaration> dataSetIdentifierDeclarationsOld) {

        List<DataSetIdentifierDeclaration> dataSetIdentifierDeclarationsUpdated = new ArrayList<>();
        dataSetIdentifierDeclarationsOld.forEach(dataSetIdentifierDeclarationsUpdated::add);

        DataSetIdentifierDeclaration addition = DataSetIdentifierDeclaration.builder()
                .dataSetArn("arn:aws:quicksight:us-east-1:661410064369:dataset/dataset04sdk")
                .identifier("bbb")
                .build();

        dataSetIdentifierDeclarationsUpdated.add(addition);
        return dataSetIdentifierDeclarationsUpdated;
    }


    private static List<SheetDefinition> sheetDefinition(List<SheetDefinition> sheetsOld) {
        List<SheetDefinition> sheetDefinitionsNew = new ArrayList<>();
        sheetsOld.forEach(sheetDefinitionsNew::add);

        SheetDefinition addition = SheetDefinition.builder()
                .sheetId("analysis1_sdk_sheet2")    // sheet N
                .name("analysis1_sdk_sheet2")
                .visuals(visuals())
                .layouts(layouts())
                .contentType(SheetContentType.INTERACTIVE)
                .build();

        sheetDefinitionsNew.add(addition);
        return sheetDefinitionsNew;
    }


    private static Visual visuals() {


        return Visual.builder()
                .tableVisual(TableVisual.builder()
                        .visualId("analysis1_sdk_sheet2_visual1")
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
                        .dataSetIdentifier("bbb")
                        .columnName("Name")
                        .build())
                .build();
        UnaggregatedField unaggregatedField2 = UnaggregatedField.builder()
                .fieldId("field2")
                .column(ColumnIdentifier.builder()
                        .dataSetIdentifier("bbb")
                        .columnName("Age")
                        .build())
                .build();
        UnaggregatedField unaggregatedField3 = UnaggregatedField.builder()
                .fieldId("field3")
                .column(ColumnIdentifier.builder()
                        .dataSetIdentifier("bbb")
                        .columnName("Weight")
                        .build())
                .build();
        return TableUnaggregatedFieldWells.builder()
                .values(unaggregatedField1, unaggregatedField2, unaggregatedField3)
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
                                .elementId("analysis1_sdk_sheet2_visual1")
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


    private static List<ColumnConfiguration> columnConfigurations(List<ColumnConfiguration> columnConfigurationsOld) {

        List<ColumnConfiguration> columnConfigurationsNew = new ArrayList<>();
        columnConfigurationsOld.forEach(columnConfigurationsNew::add);

        ColumnIdentifier columnIdentifierNew = ColumnIdentifier.builder()
                .dataSetIdentifier("bbb")
                .columnName("Name")
                .build();

        ColumnConfiguration columnConfigurationNew = ColumnConfiguration.builder()
                .column(columnIdentifierNew)
                .role(ColumnRole.MEASURE)
                .build();

        columnConfigurationsNew.add(columnConfigurationNew);
        return columnConfigurationsNew;
    }

}
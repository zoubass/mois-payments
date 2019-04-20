import React, {Component} from 'react';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_dark from "@amcharts/amcharts4/themes/dark";

am4core.useTheme(am4themes_dark);

export class ChartMain extends Component {
    componentDidMount() {
        let chart = am4core.create("chartdiv", am4charts.XYChart);

        const monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];

        let data = [];
        let totCash = 0;
        for (let i = 0; i < 12; i++) {
            totCash = Math.round((Math.random() > 0 ? 1 : 0) * Math.random() * 100000);
            data.push({month: monthNames[i], cash: totCash});
        }
        chart.data = data;

// Create axes
        let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
        categoryAxis.dataFields.category = "month";
        categoryAxis.renderer.labels.template.rotation = 270;
        categoryAxis.renderer.labels.template.hideOversized = false;
        categoryAxis.renderer.minGridDistance = 20;
        categoryAxis.renderer.labels.template.horizontalCenter = "right";
        categoryAxis.renderer.labels.template.verticalCenter = "middle";
        categoryAxis.tooltip.label.rotation = 270;
        categoryAxis.tooltip.label.horizontalCenter = "right";
        categoryAxis.tooltip.label.verticalCenter = "middle";

        let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
        valueAxis.title.text = "Cash";
        valueAxis.title.fontWeight = "bold";
// Create series
        let series = chart.series.push(new am4charts.ColumnSeries3D());
        series.dataFields.valueY = "cash";
        series.dataFields.categoryX = "month";
        series.name = "Cash";
        series.tooltipText = "{categoryX}: [bold]{valueY}[/]";
        series.columns.template.fillOpacity = .8;

        let columnTemplate = series.columns.template;
        columnTemplate.strokeWidth = 2;
        columnTemplate.strokeOpacity = 1;
        columnTemplate.stroke = am4core.color("#s");

        columnTemplate.adapter.add("fill", (fill, target) => {
            return chart.colors.getIndex(target.dataItem.index);
        })

        columnTemplate.adapter.add("stroke", (stroke, target) => {
            return chart.colors.getIndex(target.dataItem.index);
        })

        chart.cursor = new am4charts.XYCursor();
        chart.cursor.lineX.strokeOpacity = 0;
        chart.cursor.lineY.strokeOpacity = 0;

        this.chart = chart;
    }

    componentDidUpdate(oldProps) {
        if (oldProps.paddingRight !== this.props.paddingRight) {
            this.chart.paddingRight = this.props.paddingRight;
        }
    }

    componentWillUnmount() {
        if (this.chart) {
            this.chart.dispose();
        }
    }

    render() {
        return (
            <div className="Chart-styles">
                <div id="chartdiv" style={{width: "100%", height: "500px"}}>asd</div>
            </div>

        );
    }
}
import React, {Component} from 'react';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_dark from "@amcharts/amcharts4/themes/dark";

am4core.useTheme(am4themes_dark);

export class ChartMain extends Component {
    state = {
        monthData: [],
        isClicked: false
    };

    componentDidMount() {
        let chart = am4core.create("chartdiv", am4charts.XYChart);
        chart.data = this.props.barChartData;

// Create axes
        let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
        categoryAxis.dataFields.category = "monthInYear";
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
        valueAxis.min = 0;
// Create series
        let series = chart.series.push(new am4charts.ColumnSeries3D());
        series.dataFields.valueY = "value";
        series.dataFields.categoryX = "monthInYear";
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

        series.columns.template.events.on("hit", async function (ev) {
            let month = ev.target.column3D.dataItem.categories;
            const responseMonth = await fetch("/getMonthItem/" + Object.values(month));
            const bodyMonth = await responseMonth.json();
            console.log("click bar: ", bodyMonth);

            this.props.history.push({
                pathname: '/month',
                state:{
                    monthData: bodyMonth
                }
            })
        }, this);

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
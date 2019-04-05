import React, { Component } from 'react';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";



export class PieChart extends Component {
    
    async componentDidMount() {
        am4core.useTheme(am4themes_animated);
        var chart = am4core.create("chartdiv", am4charts.PieChart3D);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in


        //*************************************
        //nastaveni dat z props
        //*************************************
        chart.data = this.props.summaryData;

        chart.innerRadius = am4core.percent(50);

        chart.legend = new am4charts.Legend();
        chart.legend.position = "right";


        var series = chart.series.push(new am4charts.PieSeries3D());

        //*************************************
        //jak se jmenujou promenne v jsonu, ve kterych ma graf hledat hodnoty
        //*************************************
        series.dataFields.value = "value";
        series.dataFields.category = "name";
        series.slices.template.cornerRadius = 10;
        series.colors.step = 1;

        this.chart = chart;
    }

    componentWillUnmount() {
        if (this.chart) {
            this.chart.dispose();
        }
    }


    render() {
        return (
                <div>
            <div id="chartdiv" ></div>
                </div>
        );
    }
}
import { addComma } from "./utilCommon";
import { ChartSeriesType } from "interface/InterfaceDashboard";

export const chartOption = (
  date: Array<string | number>,
  dataSeries: Array<ChartSeriesType>,
  chartType?: string | null,
) => {
  let chartData = {
    series: [...dataSeries],
    options: {
      chart: {
        type: "bar",
        toolbar: {
          tools: {
            download: true,
          },
        },
        show: true,
      },
      legend: {
        fontSize: "15px",
      },
      plotOptions: {
        bar: {
          horizontal: false,
          dataLabels: {
            position: "top",
          },
        },
      },
      colors: ["#0c1e7f", "#0d6efd"],

      dataLabels: {
        enabled: chartType === "remittance" ? false : true,
        offsetUY: 6,
        style: {
          fontSize: "12px",
          colors: ["#fff"],
        },
      },
      stroke: {
        show: false,
        width: 1,
        colors: ["#000"],
      },
      xaxis: {
        categories: date,
      },
      yaxis: {
        show: true,
        labels: {
          formatter: function (val: number) {
            return addComma(val);
          },
        },
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val: number) {
            return addComma(val);
          },
        },
      },
    },
  };

  return chartData;
};

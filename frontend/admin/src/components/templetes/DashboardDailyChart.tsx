import { useState, useEffect } from "react";
import { Card, CardBody, Input, Button } from "reactstrap";
// util
import { selectYear, selectMonth } from "utils/utilDate";
import { addZero } from "utils/utilCommon";
import { chartOption } from "utils/utilChart";
// css
import styles from "assets/css/Dashboard.module.css";
// chart
import ApexCharts from "react-apexcharts";
// dummyData
import { dummyChart } from "data/dummyData";
// interface
import { DailyChartProps } from "interface/interfaceDashboard";

const DashboardDailyChart = () => {
  const [thisMonthArr, setThisMonthArr] = useState<Array<DailyChartProps>>([]);
  const [selectDate, setSelectDate] = useState({ year: 0, month: 0 });
  const [selectChart, setSelectChart] = useState<number>(0);
  const buttonArr = ["회원 / 인증", "송금건수", "송금액"];
  const getLatestDate = new Date(
    selectDate.year,
    selectDate.month,
    0,
  ).getDate();
  const windowHeight = window.innerHeight;

  useEffect(() => {
    setThisMonthArr(dummyChart);
  }, []);

  const checkAllMonth = () => {
    // 이 달의 전체 일 보여주기
    const moreAddDate = getLatestDate - thisMonthArr.length;

    const allDateArr = thisMonthArr.slice();

    for (let i = 0; i < moreAddDate; i++) {
      allDateArr.push({
        day: allDateArr.length + 1,
        userCount: null,
        certUserCount: null,
        txnCount: null,
        remittance: null,
      });
    }

    return allDateArr;
  };

  const arrChartData = (key: string, addText: null | string = null) => {
    if (addText === null) {
      return checkAllMonth().map((el: any) => Number(el[key]));
    } else {
      return checkAllMonth().map(
        (el: any, idx: number) =>
          addZero(el[key] === undefined ? String(idx) : String(el[key])) +
          addText,
      );
    }
  };

  const thisMonthDate = arrChartData("day", "일");

  const onClickChart = (value: number) => {
    setSelectChart(value);
  };

  const handleDate = (
    e: React.ChangeEvent<HTMLSelectElement> | any,
    date: string,
  ) => {
    if (date === "year") {
      setSelectDate({ ...selectDate, year: e.target.value });
    } else {
      setSelectDate({ ...selectDate, month: e.target.value });
    }
  };

  const chartData: any = () => {
    if (selectChart === 0) {
      return chartOption(thisMonthDate, [
        { name: "가입명수", data: arrChartData("userCount") },
        { name: "인증명수", data: arrChartData("certUserCount") },
      ]);
    } else if (selectChart === 1) {
      return chartOption(thisMonthDate, [
        {
          name: "송금완료건수",
          data: arrChartData("txnCount"),
        },
      ]);
    } else {
      return chartOption(
        thisMonthDate,
        [
          {
            name: "송금액수",
            data: arrChartData("remittance"),
          },
        ],
        "remittance",
      );
    }
  };

  return (
    <Card>
      <CardBody>
        <div className={styles.chartTopWrap}>
          <span className={`boxTitle ${styles.chartTitle}`}>Daily Graph</span>
          <div className={`alignRow ${styles.selectBtnWrap}`}>
            <div className={`${styles.btnWrap} `}>
              {buttonArr.map((el, idx) => (
                <Button
                  color="primary"
                  outline={selectChart !== idx}
                  type="button"
                  className="textHidden"
                  key={idx}
                  onClick={() => onClickChart(idx)}>
                  {el}
                </Button>
              ))}
            </div>
            <div className={`alignRow ${styles.selectWrap}`}>
              <Input
                type="select"
                name="select"
                onChange={(e) => handleDate(e, "year")}>
                {selectYear().map((el, idx) => (
                  <option key={idx} value={el}>
                    {el}
                  </option>
                ))}
              </Input>
              <Input
                type="select"
                name="select"
                onChange={(e) => handleDate(e, "month")}>
                {selectMonth().map((el, idx) => (
                  <option key={idx} value={el}>
                    {el}
                  </option>
                ))}
              </Input>
            </div>
          </div>
        </div>
        <ApexCharts
          series={chartData().series}
          options={chartData().options}
          type="bar"
          height={windowHeight / 2}
        />
      </CardBody>
    </Card>
  );
};

export default DashboardDailyChart;

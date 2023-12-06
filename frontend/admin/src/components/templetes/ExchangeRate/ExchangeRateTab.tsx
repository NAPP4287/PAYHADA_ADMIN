import { useState, useEffect } from "react";
// component
import Tab from "components/layout/Tab";
import ListFilter from "components/layout/ListFilter";
import FilterCalendar from "components/atomic/organisms/FilterCalendar";
import LabelSelect from "components/atomic/atoms/LabelSelect";
// data
import { receiveMethodList } from "data/radioCheckListData";
import { SELECT_COUNTRY_LIST } from "data/constantsData";
import { Card, CardBody } from "reactstrap";

const ExchangeRateTab = () => {
  useEffect(() => {
    console.log(changeCountry, changeRecv);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getFilter = (): void => {
    console.log("filter 조회");
  };

  const [changeCountry, setChangeCountry] = useState("");
  const [changeRecv, setChangeRecv] = useState("");

  const columnArr = [
    [{ title: "조회기간", content: <FilterCalendar />, flexRate: 1 }],
    [
      {
        title: "국가",
        content: (
          <LabelSelect
            type={"select"}
            setChangeData={setChangeCountry}
            value={SELECT_COUNTRY_LIST[0].name}
            dataArray={SELECT_COUNTRY_LIST}
            border={"none"}
          />
        ),
        flexRate: 1,
      },
      {
        title: "수취방법",
        content: (
          <LabelSelect
            type={"select"}
            setChangeData={setChangeRecv}
            value={receiveMethodList[0].name}
            dataArray={receiveMethodList}
            border={"none"}
          />
        ),
        flexRate: 1,
      },
    ],
  ];

  const tabList = [
    {
      title: "환율",
      content: (
        <ListFilter filterTableList={columnArr} action={getFilter} tab />
      ),
    },
    {
      title: "승인",
      content: (
        <ListFilter filterTableList={columnArr} action={getFilter} tab />
      ),
    },
  ];

  return (
    <>
      <Tab tabList={tabList} />
      <div className="marginTop">
        <Card>
          <CardBody></CardBody>
        </Card>
      </div>
    </>
  );
};

export default ExchangeRateTab;

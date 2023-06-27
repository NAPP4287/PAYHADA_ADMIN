// components
import ListFilter from "components/layout/ListFilter";
import ListTable from "components/layout/ListTable";
import FilterCalendar from "components/atomic/organisms/FilterCalendar";
import RadioButtons from "components/atomic/organisms/RadioButtons";
import LabelInput from "components/atomic/atoms/LabelInput";
// data
import { TXN_TABLE_INFO } from "data/tableInfoData";
import { statusList } from "data/radioCheckListData";
import { useState } from "react";

const RemitList = (props: any) => {
  const { list } = props;

  const [chageRadio, setChangeRadio] = useState("");
  const [searchInput, setSearchInput] = useState("");

  const changeValue = (e: React.ChangeEvent<HTMLInputElement>) => {
    setChangeRadio(e.target.value);
  };

  const getFilter = () => {
    console.log("filter 조회");
  };

  const testFunction = () => {
    console.log("언제든 바뀔 수 있는 버튼");
  };

  const columnArr = [
    [{ title: "조회기간", content: <FilterCalendar /> }],
    [
      {
        title: "거내내역",
        content: (
          <RadioButtons
            type="row"
            radioList={statusList}
            changeValue={changeValue}
            activeValue={chageRadio}
          />
        ),
      },
    ],
    [
      {
        title: "거내내역",
        content: (
          <LabelInput
            placeholder="입력"
            type="search"
            border="none"
            setChangeData={setSearchInput}
            value={searchInput}
          />
        ),
      },
      { title: "거내내역", content: "content" },
    ],
  ];

  return (
    <>
      <ListFilter filterTableList={columnArr} action={getFilter} />
      <div className="marginTop">
        <ListTable info={TXN_TABLE_INFO} list={list} action={testFunction} />
      </div>
    </>
  );
};

export default RemitList;

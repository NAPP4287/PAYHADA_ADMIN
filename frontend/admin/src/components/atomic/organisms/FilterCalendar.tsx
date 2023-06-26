import { useState } from "react";
// components
import LabelSelect from "components/atomic/atoms/LabelSelect";
import DatePickers from "components/atomic/atoms/DatePickers";
// data
import { CALENDAR_DATE_LIST } from "data/selectData";

const FilterCalendar = () => {
  const [changeDate, setChangeDate] = useState("");

  return (
    <div
      className="alignCenter"
      style={{
        justifyContent: "flex-start",
      }}>
      <LabelSelect
        type={"select"}
        setChangeData={setChangeDate}
        value={changeDate}
        dataArray={CALENDAR_DATE_LIST}
        border={"none"}
      />
      <div className="marginLeft">
        <DatePickers />
      </div>
    </div>
  );
};

export default FilterCalendar;

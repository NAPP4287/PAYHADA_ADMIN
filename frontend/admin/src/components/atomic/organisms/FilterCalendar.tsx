import { useState } from "react";
// components
import LabelSelect from "components/atomic/atoms/LabelSelect";
import DatePickers from "components/atomic/atoms/DatePickers";
// data
import { SELECT_CALENDAR_DATE } from "data/constantsData";

const FilterCalendar = () => {
  const [changeDate, setChangeDate] = useState<string>("");

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
        dataArray={SELECT_CALENDAR_DATE}
        border={"none"}
      />
      <div className="marginLeft">
        <DatePickers />
      </div>
    </div>
  );
};

export default FilterCalendar;

import { useState } from "react";
import ReactDatetime from "react-datetime";
// css
import styles from "assets/css/List.module.css";

const DatePickers = () => {
  const [selectDate, setSelectDate] = useState<any>({
    startDate: {},
    endDate: {},
  });
  const [pickerType, setPickerType] = useState<string>("");

  const handleRenderDay = (props: any, currentDate: any, type: string) => {
    let classes = props.className;
    let disableClick: boolean = false;

    const startObjLen = Object.keys(selectDate.startDate).length;
    const endObjLen = Object.keys(selectDate.endDate).length;
    if (type === "end" && startObjLen !== 0) {
      if (
        selectDate.startDate.format("YYYY-MM-DD") >
        currentDate.format("YYYY-MM-DD")
      ) {
        disableClick = true;
      }
    } else if (type === "start" && endObjLen !== 0) {
      if (
        selectDate.endDate.format("YYYY-MM-DD") <
        currentDate.format("YYYY-MM-DD")
      ) {
        disableClick = true;
      }
    }

    if (
      selectDate.startDate &&
      selectDate.startDate._d + "" === currentDate._d + ""
    ) {
      classes += " start-date";
    } else if (
      selectDate.startDate &&
      selectDate.endDate &&
      new Date(selectDate.startDate._d + "") < new Date(currentDate._d + "") &&
      new Date(selectDate.endDate._d + "") > new Date(currentDate._d + "")
    ) {
      classes += " middle-date";
    } else if (
      selectDate.endDate &&
      selectDate.endDate._d + "" === currentDate._d + ""
    ) {
      classes += " end-date";
    }

    return (
      <td key={props.key}>
        <button
          {...props}
          style={{ lineHeight: "36px" }}
          className={classes}
          disabled={disableClick}>
          {currentDate.date()}
        </button>
      </td>
    );
  };

  return (
    <div
      className="alignCenter"
      style={{
        justifyContent: "flex-start",
      }}>
      <div className={styles.datePickerWrap}>
        <i className="ni ni-calendar-grid-58" style={{ padding: "0 10px" }} />

        <ReactDatetime
          className={styles.calendarWrap}
          inputProps={{
            placeholder: "Date Picker Here",
            onClick: () => setPickerType("start"),
            style: { fontSize: "14px" },
            readOnly: true,
          }}
          timeFormat={false}
          renderDay={(props, currentDate) =>
            handleRenderDay(props, currentDate, pickerType)
          }
          onChange={(e) => setSelectDate({ ...selectDate, startDate: e })}
        />
      </div>
      <div className={styles.datePickerWrap}>
        <i className="ni ni-calendar-grid-58" style={{ padding: "0 10px" }} />

        <ReactDatetime
          className={styles.calendarWrap}
          inputProps={{
            placeholder: "Date Picker Here",
            onClick: () => setPickerType("end"),
            style: { fontSize: "14px" },
            readOnly: true,
          }}
          timeFormat={false}
          renderDay={(props, currentDate) =>
            handleRenderDay(props, currentDate, pickerType)
          }
          onChange={(e) => setSelectDate({ ...selectDate, endDate: e })}
        />
      </div>
    </div>
  );
};

export default DatePickers;

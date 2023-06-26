import { Label, Input } from "reactstrap";
import { LabelSelectProps } from "interface/InterfaceBasicLabel";

const SelectOption = (
  value: string,
  setChangeData: Function,
  dataArray: Array<string>,
  border?: string | null,
) => {
  return (
    <div>
      <Input
        type="select"
        name="select"
        className={border === "none" ? "borderNone" : ""}>
        {dataArray.map((el, idx) => (
          <option key={idx} onClick={() => setChangeData(el)}>
            {el}
          </option>
        ))}
      </Input>
    </div>
  );
};

const LabelSelect = (props: LabelSelectProps) => {
  const {
    label,
    type,
    setChangeData,
    value,
    dataArray,
    isFailed,
    failedText,
    border,
  } = props;

  return (
    <div>
      {label && (
        <Label for={type} style={{ fontSize: "13px" }}>
          {label}
        </Label>
      )}
      {SelectOption(value, setChangeData, dataArray, border)}
      {isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default LabelSelect;

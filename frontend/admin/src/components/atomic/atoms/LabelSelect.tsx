import { Label, Input } from "reactstrap";
// interface
import { LabelSelectProps, SelectArrayType } from "interface/InterfaceProps";

const SelectOption = (
  value: string,
  setChangeData: Function,
  dataArray: Array<SelectArrayType>,
  border?: string | null,
) => {
  return (
    <div>
      <Input
        type="select"
        name="select"
        className={border === "none" ? "borderNone" : ""}>
        {dataArray.map((el: SelectArrayType, idx: number) => (
          <option key={idx} onClick={() => setChangeData(el)}>
            {el.name}
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

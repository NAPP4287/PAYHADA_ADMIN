import { Label, Input } from "reactstrap";
import { LabelSelectProps } from "interface/InterfaceBasicLabel";

const SelectOption = (
  value: string,
  setChangeData: Function,
  dataArray: Array<string>,
) => {
  return (
    <div>
      <Input type="select" name="select">
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
  const { label, type, setChangeData, value, dataArray, isFailed, failedText } =
    props;

  return (
    <>
      {label && (
        <Label for={type} style={{ fontSize: "13px" }}>
          {label}
        </Label>
      )}
      {SelectOption(value, setChangeData, dataArray)}
      {isFailed && <span className="errorText">{failedText}</span>}
    </>
  );
};

export default LabelSelect;

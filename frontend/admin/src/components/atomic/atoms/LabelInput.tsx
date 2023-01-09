import { Input, Label } from "reactstrap";
import { LabelInputProps } from "interface/InterfaceBasicLabel";

const LabelInput = (props: LabelInputProps) => {
  const {
    label,
    placeholder,
    type,
    setChangeData,
    value,
    isFailed,
    failedText,
    maxLength,
    labelInputStyle,
  } = props;

  const inputChange = (e: any) => {
    setChangeData(e.target.value);
  };

  return (
    <div className="marginBottom" style={{ ...labelInputStyle }}>
      <Label for={type} className="label">
        {label}
      </Label>
      <Input
        type={type}
        placeholder={placeholder}
        onChange={(e) => inputChange(e)}
        value={value}
        maxLength={maxLength}
      />
      {!isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default LabelInput;

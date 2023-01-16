import { Input, Label } from "reactstrap";
import { LabelInputProps } from "interface/InterfaceBasicLabel";
import { onKeyPressEnter } from "utils/utilInput";

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
    onEnter,
  } = props;

  const inputChange = (e: any) => {
    setChangeData(e.target.value);
  };

  return (
    <div className="marginBottom">
      <Label for={type} className="label">
        {label}
      </Label>
      <Input
        type={type}
        placeholder={placeholder}
        onChange={(e) => inputChange(e)}
        value={value}
        maxLength={maxLength}
        onKeyPress={(e) => onKeyPressEnter(e, onEnter)}
      />
      {!isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default LabelInput;

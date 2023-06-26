import { Input, Label } from "reactstrap";
import { LabelInputProps } from "interface/InterfaceBasicLabel";
import React from "react";

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
    border,
    // onEnter,
  } = props;

  const inputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setChangeData(e.target.value);
  };

  return (
    <div
      className={`${border === "none" ? "alignCenter" : "marginBottom"}`}
      style={{ width: "100%" }}>
      {label && (
        <Label for={type} className="label">
          {label}
        </Label>
      )}
      <Input
        type={type}
        placeholder={placeholder}
        onChange={(e) => inputChange(e)}
        value={value}
        maxLength={maxLength || undefined}
        className={border === "none" ? "borderNone" : ""}
        id="exampleFormControlInput1"
        // onKeyPress={(e) => onKeyPressEnter(e, onEnter)}
      />
      {!isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default LabelInput;

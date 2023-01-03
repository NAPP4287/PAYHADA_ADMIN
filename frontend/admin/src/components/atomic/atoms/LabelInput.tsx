import { Input, Label, FormGroup } from "reactstrap";
import { LabelInputProps } from "interface/InterfaceBasicLabel";

function LabelInput(props: LabelInputProps) {
  const { label, placeholder, type, setChangeData, value } = props;

  const inputChange = (e: any) => {
    setChangeData(e.target.value);
  };

  return (
    <FormGroup>
      <Label for={type} style={{ fontSize: "13px" }}>
        {label}
      </Label>
      <Input
        type={type}
        placeholder={placeholder}
        onChange={(e) => inputChange(e)}
        value={value}
      />
    </FormGroup>
  );
}

export default LabelInput;

import {
  Label,
  FormGroup,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";
import { LabelSelectProps } from "interface/InterfaceBasicLabel";

const SelectOption = (
  value: string,
  setChangeData: Function,
  dataArray: Array<string>,
) => {
  return (
    <UncontrolledDropdown group className="fullWidth">
      <DropdownToggle
        outline
        caret
        style={{
          textAlign: "left",
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          border: "1px solid #ced4da",
          height: "43px",
        }}>
        {value}
      </DropdownToggle>
      <DropdownMenu className="fullWidth">
        {dataArray.map((el, idx) => (
          <DropdownItem key={idx} onClick={() => setChangeData(el)}>
            {el}
          </DropdownItem>
        ))}
      </DropdownMenu>
    </UncontrolledDropdown>
  );
};

function LabelSelect(props: LabelSelectProps) {
  const { label, type, setChangeData, value, dataArray } = props;

  return (
    <FormGroup>
      <Label for={type} style={{ fontSize: "13px" }}>
        {label}
      </Label>
      {SelectOption(value, setChangeData, dataArray)}
    </FormGroup>
  );
}

export default LabelSelect;

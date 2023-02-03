import { RadioButtonProps } from "interface/interfaceButton";

const RadioButton = (props: RadioButtonProps) => {
  const { label, value, changeValue, activeValue } = props;

  return (
    <div className="custom-control custom-radio" style={{ flex: 1 }}>
      <input
        className="custom-control-input"
        name="custom-radio-2"
        type="radio"
        id={label}
        value={value}
        onChange={(e) => changeValue(e)}
        defaultChecked={activeValue === value}
      />
      <label className="custom-control-label" htmlFor={label}>
        {label}
      </label>
    </div>
  );
};

export default RadioButton;

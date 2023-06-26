import { RadioButtonProps } from "interface/interfaceButton";

const RadioButton = (props: RadioButtonProps) => {
  const { label, value, changeValue, activeValue } = props;

  return (
    <div style={{ flex: 1 }} className="marginRight">
      <label htmlFor={value}>
        <input
          type="checkbox"
          className="checkbox"
          id={value}
          value={value}
          onChange={(e) => changeValue(e)}
          checked={activeValue === value}
        />
        <span>{label}</span>
      </label>
    </div>
  );
};

export default RadioButton;

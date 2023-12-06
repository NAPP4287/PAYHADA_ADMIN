import { RadioButtonProps } from "interface/InterfaceProps";

const RadioButton = (props: RadioButtonProps) => {
  const { label, value, changeValue, activeValue } = props;

  return (
    <div className="marginRight">
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

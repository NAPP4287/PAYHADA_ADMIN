import RadioButton from "components/atomic/atoms/RadioButton";
// interfaces
import { RadioListProps } from "interface/InterfaceProps";

const RadioButtons = (props: RadioListProps) => {
  const { radioList, type, changeValue, activeValue } = props;

  return (
    <div className={type === "row" ? "alignRow" : ""}>
      {radioList.map((el, idx: number) => (
        <RadioButton
          key={idx}
          label={el.label}
          value={el.value}
          changeValue={changeValue}
          activeValue={activeValue}
        />
      ))}
    </div>
  );
};

export default RadioButtons;

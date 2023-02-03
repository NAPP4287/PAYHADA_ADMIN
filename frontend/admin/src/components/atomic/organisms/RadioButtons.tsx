import RadioButton from "../atoms/RadioButton";
// interfaces
import { RadioListProps } from "interface/interfaceButton";

const RadioButtons = (props: RadioListProps) => {
  const { radioList, type, changeValue } = props;

  return (
    <div className={type === "row" ? "alignRow" : ""}>
      {radioList.map((el, idx: number) => (
        <RadioButton
          key={idx}
          idx={idx}
          label={el.label}
          value={el.value}
          changeValue={changeValue}
        />
      ))}
    </div>
  );
};

export default RadioButtons;

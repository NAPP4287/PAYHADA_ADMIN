import { InputTimerProps } from "interface/InterfaceBasicLabel";
import { Label } from "reactstrap";
import Timer from "components/atomic/atoms/Timer";

const InputTimer = (props: InputTimerProps) => {
  const {
    label,
    type,
    setChangeData,
    value,
    isFailed,
    maxLength,
    labelInputStyle,
    placeholder,
    failedText,
    minutes,
    seconds,
    setMinutes,
    setSeconds,
    timerStyle,
  } = props;

  const inputChange = (e: any) => {
    setChangeData(e.target.value);
    console.log(e.target.value);
  };

  return (
    <div style={{ ...labelInputStyle }}>
      <Label for={type} className="label">
        {label}
      </Label>
      <div className="inputWrap modalHeader">
        <input
          className="inputNone"
          placeholder={placeholder}
          type={type}
          onChange={(e) => inputChange(e)}
          value={value}
          maxLength={maxLength}
        />
        <Timer
          minutes={minutes}
          seconds={seconds}
          setMinutes={setMinutes}
          setSeconds={setSeconds}
          timerStyle={timerStyle}
        />
      </div>
      {!isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default InputTimer;

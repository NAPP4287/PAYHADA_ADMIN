import { InputTimerProps } from "interface/InterfaceBasicLabel";
import { Button, Label } from "reactstrap";
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
    setIsEnd,
    isEnd,
  } = props;

  const inputChange = (e: any) => {
    setChangeData(e.target.value);
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
        {isEnd ? (
          <Button color="secondary" type="button" className="label">
            재요청
          </Button>
        ) : (
          <Timer
            minutes={minutes}
            seconds={seconds}
            setMinutes={setMinutes}
            setSeconds={setSeconds}
            timerStyle={timerStyle}
            setIsEnd={setIsEnd}
            isEnd={isEnd}
          />
        )}
      </div>
      {!isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default InputTimer;
